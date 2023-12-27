package com.zl.community.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author : ZL
 */
@Data
@ApiModel("根据用户名or电话or邮箱查询模型")
public class OperateByUserNameOrPhoneOrEmailModel implements Serializable {
    @ApiModelProperty(value = "nameOrPhoneOrEmail", required = true)
    private String nameOrPhoneOrEmail;

    private static final long serialVersionUID = 1L;
}
