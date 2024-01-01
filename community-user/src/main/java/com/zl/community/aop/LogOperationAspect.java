package com.zl.community.aop;

import com.zl.community.annotation.LogOperation;
import com.zl.community.model.entity.OperationLogEntity;
import com.zl.community.model.vo.UserPrincipal;
import com.zl.community.service.impl.OperationLogServiceImpl;
import com.zl.community.util.IpUtils;
import com.zl.community.util.JacksonUtils;
import com.zl.community.util.ServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.zl.community.constant.Constants.UserConstant.DISABLE;
import static com.zl.community.constant.Constants.UserConstant.ENABLE;

/**
 * @Author : ZL
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LogOperationAspect {
    private final OperationLogServiceImpl operationLogService;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.zl.community.annotation.LogOperation)")
    public void logPointCut() {
    }

    /**
     * 通知处理
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            // 调用目标方法，不写point.proceed()，方法不会调用,将结果返回
            Object result = point.proceed();

            // 计算执行时长
            long time = System.currentTimeMillis() - beginTime;

            // 保存日志
            saveLog(point, time, ENABLE);

            return result;
        } catch (Exception e) {
            // 计算执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            // 保存日志
            saveLog(point, time, DISABLE);

            throw e;
        }

    }

    // 保存日志
    public void saveLog(ProceedingJoinPoint joinPoint, long time, Integer status) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        LogOperation annotation = method.getAnnotation(LogOperation.class);
        // 获取操作日志对象
        Class<OperationLogEntity> operationLogEntityClass = OperationLogEntity.class;
        OperationLogEntity operationLogEntity = operationLogEntityClass.newInstance();
        if (!Objects.isNull(annotation)) {
            operationLogEntity.setOperation(annotation.value());
        }
        // 获取当前登录用户
        UserPrincipal loginUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        operationLogEntity.setUsername(loginUser.getUsername());
        operationLogEntity.setRequestTime((int) time);
        operationLogEntity.setStatus(status);
        operationLogEntity.setCreateId(loginUser.getId());
        operationLogEntity.setUpdateId(loginUser.getId());
        HttpServletRequest request = ServletUtils.getRequest();
        operationLogEntity.setIp(IpUtils.getIpAddr(request));
        operationLogEntity.setRequestUri(request.getRequestURI());
        operationLogEntity.setRequestMethod(request.getMethod());

        // 获取参数
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            if (args[0] instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) args[0];
                String fileName = file.getOriginalFilename();
                long fileSize = file.getSize();
                // 将文件名和文件大小等信息记录到operationLogEntity中
                operationLogEntity.setRequestParams("文件名：" + fileName + "，文件大小：" + fileSize);
            } else {
                String params = JacksonUtils.toJson(args[0]);
                operationLogEntity.setRequestParams(params);
            }
        } else {
            //这里处理请求没有携带任何请求参数的情况
            operationLogEntity.setRequestParams("当前请求没有携带任何的请求参数");
        }


        // 保存数据
        operationLogService.save(operationLogEntity);

    }
}
