package com.zl.community.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author : ZL
 */
@Data
@ApiModel("用户登录模型")
public class LoginRequestModel implements Serializable {
    /**
     * 用户名或邮箱或手机号
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名或邮箱或手机号", required = true)
    private String usernameOrEmailOrPhone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = false;

    private static final long serialVersionUID = 1L;
}
