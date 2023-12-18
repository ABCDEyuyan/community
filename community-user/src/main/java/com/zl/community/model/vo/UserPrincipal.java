package com.zl.community.model.vo;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.zl.community.exception.BusinessException;
import com.zl.community.model.entity.PermissionEntity;
import com.zl.community.model.entity.RoleEntity;
import com.zl.community.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;
import static com.zl.community.constant.Constants.UserConstant.ENABLE;

/**
 * @Author : ZL
 * 用户角色权限模型
 * @TableName user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements Serializable, UserDetails {
    /**
     * id
     */
    @TableId(value = "id", type = ASSIGN_ID)
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "user_account")
    private String userAccount;

    /**
     * 密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 用户昵称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户头像
     */
    @TableField(value = "user_avatar")
    private String userAvatar;

    /**
     * 用户简介
     */
    @TableField(value = "user_profile")
    private String userProfile;

    /**
     * 地区
     */
    @TableField(value = "user_place")
    private String userPlace;

    /**
     * 生日
     */
    @TableField(value = "user_birthday")
    private LocalDate userBirthday;

    /**
     * 主攻方向
     */
    @TableField(value = "user_direction")
    private String userDirection;

    /**
     * 目标
     */
    @TableField(value = "user_target")
    private String userTarget;

    /**
     * 学校
     */
    @TableField(value = "user_school")
    private String userSchool;

    /**
     * 专业
     */
    @TableField(value = "user_major")
    private String userMajor;

    /**
     * 学历
     */
    @TableField(value = "user_education")
    private String userEducation;

    /**
     * 毕业年份
     */
    @TableField(value = "graduation_year")
    private LocalDate graduationYear;

    /**
     * 公司
     */
    @TableField(value = "user_company")
    private String userCompany;

    /**
     * 岗位
     */
    @TableField(value = "user_station")
    private String userStation;

    /**
     * 工作年限
     */
    @TableField(value = "working_year")
    private LocalDate workingYear;

    /**
     * 用户角色 0 - 普通用户/1 - 管理员/2 - 超级管理员
     */
    @TableField(value = "user_role")
    private Integer userRole;

    /**
     * 性别 0 - 男/1 - 女
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 积分
     */
    @TableField(value = "points")
    private Integer points;

    /**
     * 状态 0 - 正常 1-封号
     */
    @TableField(value = "user_status")
    private Integer userStatus;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 是否已删除 0-未删除 1-软删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 用户角色列表
     */
    @TableField(exist = false)
    private List<String> roles;

    /**
     * 用户权限列表
     */
    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 创建用户角色权限之间的关系
     *
     * @param users
     * @param roles
     * @param permissions
     * @return 返回用户角色权限模型
     */
    public static UserPrincipal create(UserEntity users, List<RoleEntity> roles, List<PermissionEntity> permissions) {
        List<String> roleNames = roles.stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toList());

        List<GrantedAuthority> authorities = permissions.stream()
                .filter(permission -> StrUtil.isNotBlank(permission.getPermission()))
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        UserPrincipal userPrincipal=new UserPrincipal();
        try {
            BeanUtils.copyProperties(users,userPrincipal);
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
        userPrincipal.setRoles(roleNames);
        userPrincipal.setAuthorities(authorities);
        return userPrincipal;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userName=").append(userName);
        sb.append(", userAvatar=").append(userAvatar);
        sb.append(", userProfile=").append(userProfile);
        sb.append(", userPlace=").append(userPlace);
        sb.append(", userBirthday=").append(userBirthday);
        sb.append(", userDirection=").append(userDirection);
        sb.append(", userTarget=").append(userTarget);
        sb.append(", userSchool=").append(userSchool);
        sb.append(", userMajor=").append(userMajor);
        sb.append(", userEducation=").append(userEducation);
        sb.append(", graduationYear=").append(graduationYear);
        sb.append(", userCompany=").append(userCompany);
        sb.append(", userStation=").append(userStation);
        sb.append(", workingYear=").append(workingYear);
        sb.append(", userRole=").append(userRole);
        sb.append(", gender=").append(gender);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", points=").append(points);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.userStatus, ENABLE);
    }
}