package com.zl.community.model.swagger;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* 用户
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@ApiModel(value="UserApi对象", description="用户")
public class UserApi implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("账号")
    private String userAccount;

    @ApiModelProperty("密码")
    private String userPassword;

    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("用户头像")
    private String userAvatar;

    @ApiModelProperty("用户简介")
    private String userProfile;

    @ApiModelProperty("地区")
    private String userPlace;

    @ApiModelProperty("生日")
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
    private Integer gender;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
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