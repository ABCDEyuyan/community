package com.zl.community.config.security;

import com.zl.community.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author : ZL
 */
@Configuration
@RequiredArgsConstructor
public class LogoutSuccessHandlerConfig implements LogoutSuccessHandler{
    private final JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String jwtFromRequest = jwtUtils.getJwtFromRequest(request);
        if(!Objects.isNull(jwtFromRequest)){
            jwtUtils.invalidateJWT(request);
        }
    }
}
