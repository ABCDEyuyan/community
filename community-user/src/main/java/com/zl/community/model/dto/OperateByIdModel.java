package com.zl.community.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author : ZL
 */
@Data
@ApiModel("根据主键id删除模型")
public class OperateByIdModel implements Serializable {
    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    private static final long serialVersionUID = 1L;
}
