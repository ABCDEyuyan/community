package com.zl.community.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.zl.community.config.security.CustomConfig;
import com.zl.community.exception.SecurityException;
import com.zl.community.service.security.CustomUserDetailsServiceImpl;
import com.zl.community.util.JwtUtils;
import com.zl.community.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static com.zl.community.common.ResultCode.NOT_LOGIN_ERROR;

/**
 * @Author : ZL
 * jwt验证过滤器
 * 继承OncePerRequestFilter
 * OncePerRequestFilter是Spring Security中的一个过滤器，它确保在每个请求中只会执行一次过滤逻辑。
 * 具体来说，它会在doFilterInternal方法中记录一个请求是否已经被过滤过，如果已经被过滤过，则不会再次执行过滤逻辑。
 * 这样可以避免重复执行过滤逻辑，提高应用程序的性能和安全性。
 *
 * 实现JWT身份验证。它会从请求中获取JWT，然后使用UserDetailsService加载用户信息，并将其转换为UsernamePasswordAuthenticationToken，
 * 然后将其设置到SecurityContextHolder中，以便在后续的请求中使用。
 * 如果JWT无效，则会返回40100 未登录响应。如果请求不需要进行权限拦截，则会直接调用FilterChain中的doFilter方法。
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
    private final JwtUtils jwtUtil;
    private final CustomConfig customConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * 这里是对请求方式判断 可以在yml文件中配置放行地址 同时需要在securityConfig中配置放行，但这样太麻烦 相当于单独对于放行的url多做了一次判断
         *  如果是放行的url让它继续执行后面的拦截器 否则需要验证jwt 如果没有则报未登录错误给前端
         *  或者可以选择这样做 请求来了之后直接判断是否携带jwt
         */
        if (checkIgnores(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("JwtAuthenticationFilter");
        String jwt = jwtUtil.getJwtFromRequest(request);

        if (StrUtil.isNotBlank(jwt)) {
            try {
                String username = jwtUtil.getUsernameFromJWT(jwt);

                UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(username);
                /**UsernamePasswordAuthenticationToken 是 Spring Security 提供的一种特定类型的认证令牌，用于在进行用户名密码认证时传递用户信息。
                 **userDetails 是用户详细信息对象，null 是凭据（密码），userDetails.getAuthorities() 是用户的权限列表。**/
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                /**将请求的详细信息设置到认证对象中。在后续认证过程中使用这些详细信息进行验证或记录。**/
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (SecurityException e) {
                ResponseUtil.renderJson(response, e);
            }
        }
        else {
            ResponseUtil.renderJson(response, NOT_LOGIN_ERROR, null);
        }
//        filterChain.doFilter(request, response);
    }

    /**
     * 请求是否不需要进行权限拦截
     *
     * @param request 当前请求
     * @return true - 忽略，false - 不忽略
     */
    private boolean checkIgnores(HttpServletRequest request) {
        String method = request.getMethod();

        HttpMethod httpMethod = HttpMethod.resolve(method);
        if (ObjectUtil.isNull(httpMethod)) {
            httpMethod = HttpMethod.GET;
        }

        Set<String> ignores = Sets.newHashSet();

        switch (httpMethod) {
            case GET:
                ignores.addAll(customConfig.getIgnores()
                        .getGet());
                break;
            case PUT:
                ignores.addAll(customConfig.getIgnores()
                        .getPut());
                break;
            case HEAD:
                ignores.addAll(customConfig.getIgnores()
                        .getHead());
                break;
            case POST:
                ignores.addAll(customConfig.getIgnores()
                        .getPost());
                break;
            case PATCH:
                ignores.addAll(customConfig.getIgnores()
                        .getPatch());
                break;
            case TRACE:
                ignores.addAll(customConfig.getIgnores()
                        .getTrace());
                break;
            case DELETE:
                ignores.addAll(customConfig.getIgnores()
                        .getDelete());
                break;
            case OPTIONS:
                ignores.addAll(customConfig.getIgnores()
                        .getOptions());
                break;
            default:
                break;
        }

        ignores.addAll(customConfig.getIgnores()
                .getPattern());

        if (CollUtil.isNotEmpty(ignores)) {
            for (String ignore : ignores) {
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore, method);
                if (matcher.matches(request)) {
                    return true;
                }
            }
        }

        return false;
    }
}
