package com.zl.community.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.model.dto.PageQueryModel;
import com.zl.community.model.entity.LoginLogEntity;
import com.zl.community.model.swagger.LoginLogApi;
import com.zl.community.service.LoginLogService;
import com.zl.community.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : ZL
 */
@RestController
@RequestMapping("/loginLog")
@Api(tags = "系统设置-登录日志")
@RequiredArgsConstructor
public class LoginLogController {
    private final LoginLogService loginLogService;


    @ApiOperation("登录日志分页查询")
    @GetMapping("/page")
    public String ptest(){
        return "sadfasdf";
    }


    @ApiOperation("登录日志分页查询")
    @PostMapping("/page2")
    public BaseResponse<IPage<LoginLogApi>> pageQuery(@RequestBody PageQueryModel pageQueryModel) {
        System.out.println("----------------------xxxxx");
        IPage<LoginLogEntity> iPage = loginLogService.pageQuery(pageQueryModel);
        System.out.println(iPage.getRecords());
        IPage<LoginLogApi> page = new Page<>();
        BeanUtils.copyProperties(iPage, page, "records");
        List<LoginLogApi> newRecords = iPage.getRecords().stream().map(sysLoginLog -> {
            Class<LoginLogApi> sysLoginLogClass = LoginLogApi.class;
            LoginLogApi loginLog = null;
            try {
                loginLog = sysLoginLogClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(sysLoginLog, loginLog);
            return loginLog;
        }).collect(Collectors.toList());
        page.setRecords(newRecords);
        return ResultUtils.success(ResultCode.SUCCESS, page);
    }
}
