package com.zl.community.model.swagger;

import java.io.Serializable;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author : ZL
 * 登录日志表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("login_log")
@ApiModel(value = "LoginLogApi对象", description = "登录日志表")
public class LoginLogApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("状态 0：成功 1：失败")
    private String status;

    @ApiModelProperty("操作IP")
    private String ip;

    @ApiModelProperty("操作者名称")
    private String username;

    @ApiModelProperty("登录id")
    private Long loginId;

    @ApiModelProperty("登录时间")
    private LocalDateTime loginTime;

}