package com.zl.community.config.security;

import com.zl.community.filter.JwtAuthenticationFilter;
import com.zl.community.service.security.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author : ZL
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * 用户认证处理类
     **/
    private final CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
    /**
     * jwt验证过滤器
     **/
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 退出处理类
     */
    private final LogoutSuccessHandlerConfig logoutSuccessHandler;

    /**
     * 权限认证失败类
     * 抛出 UN_AUTHORIZED,4002 未授权
     *
     * @see AuthenticationEntryPointConfig
     */
    private final AuthenticationEntryPointConfig authenticationEntryPoint;

    /**
     * 权限不足处理类l
     * 抛出 NO_AUTH_ERROR,4001 权限不足
     *
     * @see AccessDeniedHandlerConfig
     */
    private final AccessDeniedHandlerConfig accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     *
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 自定义用户认证处理类
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }

//    /**
//     * 自定义用户认证处理类
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(customUserDetailsServiceImpl)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().antMatchers("/auth/login","/loginLog/page","/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs");
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //允许跨域
                .cors()
                // 关闭 CSRF
                .and()
                .csrf().disable()
                // 异常处理
                // 认证失败处理类
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint)
                .and()
                // 认证请求
                .authorizeRequests()
                .anyRequest()
                .access("@rbacAuthorityServiceConfig.hasPermission(request,authentication)")
                .and()
                // 添加JWT filter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // 添加Logout filter
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
                .and()
                // Session 管理
                .sessionManagement()
                // 因为使用了JWT，所以这里不管理Session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    //region first第一次配置
//    /**
//     * jwt验证过滤器
//     **/
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    /**
//     * 退出处理类
//     */
//    private LogoutSuccessHandlerConfig logoutSuccessHandler;
//
//    /**
//     * 权限认证失败类
//     * 抛出 UN_AUTHORIZED,4002 未授权
//     *
//     * @see AuthenticationEntryPointConfig
//     */
//    private AuthenticationEntryPoint authenticationEntryPoint;
//
//    /**
//     * 权限不足处理类
//     * 抛出 NO_AUTH_ERROR,4001 权限不足
//     *
//     * @see AccessDeniedHandlerConfig
//     */
//    private AccessDeniedHandler accessDeniedHandler;
//
//    /**
//     * 用户认证处理类
//     **/
//    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
//
//    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AccessDeniedHandler accessDeniedHandler, AuthenticationEntryPoint authenticationEntryPoint, LogoutSuccessHandlerConfig logoutSuccessHandler, CustomUserDetailsServiceImpl customUserDetailsServiceImpl) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//        this.accessDeniedHandler = accessDeniedHandler;
//        this.authenticationEntryPoint = authenticationEntryPoint;
//        this.logoutSuccessHandler = logoutSuccessHandler;
//        this.customUserDetailsServiceImpl = customUserDetailsServiceImpl;
//    }
//
//    /**
//     * 密码加密方式
//     **/
//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 认证管理器
//     *
//     * @param authenticationConfiguration
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    /**
//     * 添加自定义用户认证处理类
//     *
//     * @return
//     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return customUserDetailsServiceImpl;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http
//                //允许跨域
//                .cors()
//                // 关闭 CSRF
//                .and()
//                .csrf().disable()
//                // 异常处理
//                // 认证失败处理类
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint)
//                .and()
////                // 登录行为由自己实现，参考 AuthController#login
////                .formLogin().disable()
////                .httpBasic().disable()
////                // 认证请求
////                .authorizeRequests()
////                // 所有请求都需要登录访问
////                .anyRequest()
////                .authenticated()
////                // RBAC 动态 url 认证
////                .and()
////                .authorizeRequests()
////                .anyRequest()
////                .access("@rbacAuthorityServiceConfig.hasPermission(request,authentication)")
//                // 认证请求
//                // 对于登录接口 允许匿名访问 anonymous 仅允许匿名用户访问,如果登录了访问 反而没权限
//                .authorizeRequests().antMatchers("/login").permitAll()
//                .and()
//                .authorizeRequests(authorize -> authorize
//                        // RBAC 动态 url 认证
//                        .anyRequest().access("@rbacAuthorityServiceConfig.hasPermission(request,authentication)")
//                )
//                // 添加JWT filter
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                // 添加Logout filter
//                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
//                .and()
//                // Session 管理
//                .sessionManagement()
//                // 因为使用了JWT，所以这里不管理Session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        // @formatter:on
//        return http.build();
//    }
//
////    @Bean
////    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
////        return new WebSecurityConfigurerAdapter() {
////            @Override
////            protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////                auth.userDetailsService(customUserDetailsServiceImpl).passwordEncoder(encoder());
////            }
////
////            /**
////             * 放行所有不需要登录就可以访问的请求，参见 AuthController
////             * 也可以在 {@link #configure(HttpSecurity)} 中配置
////             * {@code http.authorizeRequests().antMatchers("/api/auth/**").permitAll()}
////             */
////            @Override
////            public void configure(WebSecurity web) {
////                WebSecurity and = web.ignoring().and();
////
////                // 忽略 GET
////                customConfig.getIgnores().getGet().forEach(url -> and.ignoring().antMatchers(HttpMethod.GET, url));
////
////                // 忽略 POST
////                customConfig.getIgnores().getPost().forEach(url -> and.ignoring().antMatchers(HttpMethod.POST, url));
////
////                // 忽略 DELETE
////                customConfig.getIgnores().getDelete().forEach(url -> and.ignoring().antMatchers(HttpMethod.DELETE, url));
////
////                // 忽略 PUT
////                customConfig.getIgnores().getPut().forEach(url -> and.ignoring().antMatchers(HttpMethod.PUT, url));
////
////                // 忽略 HEAD
////                customConfig.getIgnores().getHead().forEach(url -> and.ignoring().antMatchers(HttpMethod.HEAD, url));
////
////                // 忽略 PATCH
////                customConfig.getIgnores().getPatch().forEach(url -> and.ignoring().antMatchers(HttpMethod.PATCH, url));
////
////                // 忽略 OPTIONS
////                customConfig.getIgnores().getOptions().forEach(url -> and.ignoring().antMatchers(HttpMethod.OPTIONS, url));
////
////                // 忽略 TRACE
////                customConfig.getIgnores().getTrace().forEach(url -> and.ignoring().antMatchers(HttpMethod.TRACE, url));
////
////                // 按照请求格式忽略
////                customConfig.getIgnores().getPattern().forEach(url -> and.ignoring().antMatchers(url));
////
////            }
////        };
////    }
    //endregion
}
