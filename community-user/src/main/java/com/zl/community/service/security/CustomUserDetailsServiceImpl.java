package com.zl.community.service.security;

import com.zl.community.mapper.TbPermissionMapper;
import com.zl.community.mapper.TbRoleMapper;
import com.zl.community.mapper.UserMapper;
import com.zl.community.model.entity.PermissionEntity;
import com.zl.community.model.entity.RoleEntity;
import com.zl.community.model.entity.UserEntity;
import com.zl.community.model.vo.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : ZL
 * 用户认证处理类
 * 使用Spring Security框架的UserDetailsService接口的实现类。
 * 重写了loadUserByUsername方法，该方法接收一个用户名、邮箱或手机号作为参数，并返回一个类型为UserDetails的对象。
 * UserDetails是Spring Security框架中表示用户信息的接口，包括用户名、密码、角色等属性。
 * 在这个方法中，你需要将查询到的用户信息封装成一个UserDetails对象，并返回给Spring Security进行身份验证。
 *
 * 除了查询用户信息之外，可以在该方法中进行其它的验证和授权操作，比如检查用户的账号状态、角色和权限等信息，以确保用户有权访问所请求的资源。
 * 在查询用户信息时未找到任何结果，该方法会抛出UsernameNotFoundException异常，表示未找到该用户。
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    private final TbRoleMapper roleMapper;
    private final TbPermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
       UserEntity users = userMapper.selectByUserNameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone, usernameOrEmailOrPhone)
               .orElseThrow(()->new UsernameNotFoundException("未找到用户信息 : "+usernameOrEmailOrPhone));
       List<RoleEntity> roles = roleMapper.getUserRolesByUserId(users.getId());
        List<Long> roleIds = roles.stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toList());
        List<PermissionEntity> permissions = permissionMapper.getDistinctPermissionsByRoleIds(roleIds);
        UserPrincipal userPrincipal = UserPrincipal.create(users, roles, permissions);
        return userPrincipal;
    }
}
