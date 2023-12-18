package com.zl.community.config.security;

import com.zl.community.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zl.community.common.ResultCode.NO_AUTH_ERROR;

/**
 * @Author : ZL
 * 权限不足处理类 返回无权限
 */
@Configuration
public class AccessDeniedHandlerConfig implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtil.renderJson(response, NO_AUTH_ERROR, null);
    }
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return (request, response, accessDeniedException) -> ResponseUtil.renderJson(response, NO_AUTH_ERROR, null);
//    }
}
