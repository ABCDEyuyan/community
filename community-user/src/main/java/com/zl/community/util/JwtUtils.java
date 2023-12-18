package com.zl.community.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.zl.community.common.ResultCode;
import com.zl.community.config.JwtConfig;
import com.zl.community.exception.SecurityException;
import com.zl.community.model.vo.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.zl.community.constant.Constants.Jwt.REDIS_JWT_KEY_PREFIX;
import static com.zl.community.constant.Constants.UserConstant.HEADER;

/**
 * @Author : ZL
 */
@EnableConfigurationProperties(JwtConfig.class)
@Configuration
@Slf4j
public class JwtUtils {
    private JwtConfig jwtConfig;

    private StringRedisTemplate stringRedisTemplate;

    public JwtUtils(JwtConfig jwtConfig, StringRedisTemplate stringRedisTemplate) {
        this.jwtConfig = jwtConfig;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 创建JWT
     *
     * @param rememberMe  记住我
     * @param userAccount 用户账号
     * @param subject     用户名
     * @param roles       用户角色
     * @param authorities 用户权限
     * @return JWT
     */
    public String createJWT(Boolean rememberMe, String userAccount, String subject, List<String> roles, Collection<? extends GrantedAuthority> authorities) {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .setId(userAccount)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getKey())
                .claim("roles", roles)
                .claim("authorities", authorities);

        // 设置过期时间
        Long ttl = rememberMe ? jwtConfig.getRemember() : jwtConfig.getTtl();
        if (ttl > 0) {
            builder.setExpiration(DateUtil.offsetMillisecond(now, ttl.intValue()));
        }

        String jwt = builder.compact();
        // 将生成的JWT保存至Redis
        stringRedisTemplate.opsForValue()
                .set(REDIS_JWT_KEY_PREFIX + userAccount, jwt, ttl, TimeUnit.MILLISECONDS);
        return jwt;
    }

    /**
     * 创建JWT
     *
     * @param userPrincipal 用户认证信息
     * @param rememberMe     记住我
     * @return JWT
     */
    public String createJWT(UserPrincipal userPrincipal, Boolean rememberMe) {
        return createJWT(rememberMe, userPrincipal.getUserAccount(), userPrincipal.getUsername(), userPrincipal.getRoles(), userPrincipal.getAuthorities());
    }

    /**
     * 解析JWT
     *
     * @param jwt JWT
     * @return {@link Claims}
     */
    public Claims parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getKey())
                    .parseClaimsJws(jwt)
                    .getBody();

            //TODO:可能是claims.getUserAccount
//            String username = claims.getId();
//            String redisKey = REDIS_JWT_KEY_PREFIX + username;
            String userAccount = claims.getId();
            String redisKey = REDIS_JWT_KEY_PREFIX + userAccount;

            // 校验redis中的JWT是否存在
            Long expire = stringRedisTemplate.getExpire(redisKey, TimeUnit.MILLISECONDS);
            if (Objects.isNull(expire) || expire <= 0) {
                throw new SecurityException(ResultCode.TOKEN_EXPIRED);
            }

            // 校验redis中的JWT是否与当前的一致，不一致则代表用户已注销/用户在不同设备登录，均代表JWT已过期
                String redisToken = stringRedisTemplate.opsForValue()
                    .get(redisKey);
            if (!StrUtil.equals(jwt, redisToken)) {
                throw new SecurityException(ResultCode.TOKEN_OUT_OF_CTRL);
            }
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期");
            throw new SecurityException(ResultCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("不支持的 Token");
            throw new SecurityException(ResultCode.TOKEN_PARSE_ERROR);
        } catch (MalformedJwtException e) {
            log.error("Token 无效");
            throw new SecurityException(ResultCode.TOKEN_PARSE_ERROR);
        } catch (SignatureException e) {
            log.error("无效的 Token 签名");
            throw new SecurityException(ResultCode.TOKEN_PARSE_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在");
            throw new SecurityException(ResultCode.TOKEN_PARSE_ERROR);
        }
    }

    /**
     * 设置JWT过期
     *
     * @param request 请求
     */
    public void invalidateJWT(HttpServletRequest request) {
        String jwt = getJwtFromRequest(request);
        String userAccount = getUserAccountFromJWT(jwt);
        // 从redis中清除JWT
        stringRedisTemplate.delete(REDIS_JWT_KEY_PREFIX + userAccount);
    }

    /**
     * 根据 jwt 获取用户名
     *
     * @param jwt JWT
     * @return 用户名
     */
    public String getUsernameFromJWT(String jwt) {
        Claims claims = parseJWT(jwt);
        return claims.getSubject();
    }

    /**
     * 根据 jwt 获取用户账号
     *
     * @param jwt JWT
     * @return 用户账号
     */
    public String getUserAccountFromJWT(String jwt) {
        Claims claims = parseJWT(jwt);
        return claims.getId();
    }

    /**
     * 从 request 的 header 中获取 JWT
     *
     * @param request 请求
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER);
//        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith(jwtConfig.getKey())) {
//            return bearerToken.substring(8);
//        }
//        return null;
        System.out.println("bearerToken.substring(8) = " + bearerToken.substring(8));
        return bearerToken.substring(8);
    }


}
