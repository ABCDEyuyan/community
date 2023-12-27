package com.zl.community.model.swagger;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableName;
import org.checkerframework.checker.units.qual.Length;

import javax.validation.constraints.NotEmpty;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

/**
 * @Author : ZL
 * 用户
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@ApiModel(value = "UserApi对象", description = "用户")
public class UserApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @Length
    private Long id;

    @ApiModelProperty("账号")
    @Length
    private String userAccount;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    @Length
    private String userPassword;

    @ApiModelProperty("用户昵称")
    @NotEmpty(message = "用户昵称不能为空")
    private String userName;

    @ApiModelProperty("用户头像")
    @NotEmpty(message = "用户头像不能为空")
    private String userAvatar;

    @ApiModelProperty("用户简介")
    private String userProfile;

    @ApiModelProperty("地区")
    private String userPlace;

    @ApiModelProperty("生日")
    @NotEmpty(message = "出生年月不能为空")
    private LocalDate userBirthday;

    @ApiModelProperty("主攻方向")
    private String userDirection;

    @ApiModelProperty("目标")
    private String userTarget;

    @ApiModelProperty("学校")
    private String userSchool;

    @ApiModelProperty("专业")
    private String userMajor;

    @ApiModelProperty("学历")
    private String userEducation;

    @ApiModelProperty("毕业年份")
    private LocalDate graduationYear;

    @ApiModelProperty("公司")
    private String userCompany;

    @ApiModelProperty("岗位")
    private String userStation;

    @ApiModelProperty("工作年限")
    private LocalDate workingYear;

    @ApiModelProperty("用户角色 0 - 普通用户/1 - 管理员/2 - 超级管理员")
    private Integer userRole;

    @ApiModelProperty("性别 0 - 男/1 - 女")
    @NotEmpty(message = "性别不能为空")
    private Integer gender;

    @ApiModelProperty("电话")
    @NotEmpty(message = "电话不能为空")
    private String phone;

    @ApiModelProperty("邮箱")
    @NotEmpty(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("积分")
    private Integer points;

    @ApiModelProperty("状态 0 - 正常 1-封号")
    private Integer userStatus;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否已删除 0-未删除 1-软删除")
    private Integer isDeleted;

}