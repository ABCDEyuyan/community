package com.zl.community.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

/**
 * @Author : ZL
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class UserEntity implements Serializable {
    /**
     * id
     */
    @TableId(value = "id",type = ASSIGN_ID)
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
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserEntity other = (UserEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserAccount() == null ? other.getUserAccount() == null : this.getUserAccount().equals(other.getUserAccount()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserAvatar() == null ? other.getUserAvatar() == null : this.getUserAvatar().equals(other.getUserAvatar()))
            && (this.getUserProfile() == null ? other.getUserProfile() == null : this.getUserProfile().equals(other.getUserProfile()))
            && (this.getUserPlace() == null ? other.getUserPlace() == null : this.getUserPlace().equals(other.getUserPlace()))
            && (this.getUserBirthday() == null ? other.getUserBirthday() == null : this.getUserBirthday().equals(other.getUserBirthday()))
            && (this.getUserDirection() == null ? other.getUserDirection() == null : this.getUserDirection().equals(other.getUserDirection()))
            && (this.getUserTarget() == null ? other.getUserTarget() == null : this.getUserTarget().equals(other.getUserTarget()))
            && (this.getUserSchool() == null ? other.getUserSchool() == null : this.getUserSchool().equals(other.getUserSchool()))
            && (this.getUserMajor() == null ? other.getUserMajor() == null : this.getUserMajor().equals(other.getUserMajor()))
            && (this.getUserEducation() == null ? other.getUserEducation() == null : this.getUserEducation().equals(other.getUserEducation()))
            && (this.getGraduationYear() == null ? other.getGraduationYear() == null : this.getGraduationYear().equals(other.getGraduationYear()))
            && (this.getUserCompany() == null ? other.getUserCompany() == null : this.getUserCompany().equals(other.getUserCompany()))
            && (this.getUserStation() == null ? other.getUserStation() == null : this.getUserStation().equals(other.getUserStation()))
            && (this.getWorkingYear() == null ? other.getWorkingYear() == null : this.getWorkingYear().equals(other.getWorkingYear()))
            && (this.getUserRole() == null ? other.getUserRole() == null : this.getUserRole().equals(other.getUserRole()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPoints() == null ? other.getPoints() == null : this.getPoints().equals(other.getPoints()))
            && (this.getUserStatus() == null ? other.getUserStatus() == null : this.getUserStatus().equals(other.getUserStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserAccount() == null) ? 0 : getUserAccount().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserAvatar() == null) ? 0 : getUserAvatar().hashCode());
        result = prime * result + ((getUserProfile() == null) ? 0 : getUserProfile().hashCode());
        result = prime * result + ((getUserPlace() == null) ? 0 : getUserPlace().hashCode());
        result = prime * result + ((getUserBirthday() == null) ? 0 : getUserBirthday().hashCode());
        result = prime * result + ((getUserDirection() == null) ? 0 : getUserDirection().hashCode());
        result = prime * result + ((getUserTarget() == null) ? 0 : getUserTarget().hashCode());
        result = prime * result + ((getUserSchool() == null) ? 0 : getUserSchool().hashCode());
        result = prime * result + ((getUserMajor() == null) ? 0 : getUserMajor().hashCode());
        result = prime * result + ((getUserEducation() == null) ? 0 : getUserEducation().hashCode());
        result = prime * result + ((getGraduationYear() == null) ? 0 : getGraduationYear().hashCode());
        result = prime * result + ((getUserCompany() == null) ? 0 : getUserCompany().hashCode());
        result = prime * result + ((getUserStation() == null) ? 0 : getUserStation().hashCode());
        result = prime * result + ((getWorkingYear() == null) ? 0 : getWorkingYear().hashCode());
        result = prime * result + ((getUserRole() == null) ? 0 : getUserRole().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPoints() == null) ? 0 : getPoints().hashCode());
        result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
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

}