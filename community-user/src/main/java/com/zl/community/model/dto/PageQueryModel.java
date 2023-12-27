package com.zl.community.model.dto;

import com.zl.community.constant.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

import static com.zl.community.constant.Constants.CommonConstant.SORT_ORDER_ASC;

/**
 * @Author : ZL
 */
@Data
@ApiModel("分页模型")
public class PageQueryModel implements Serializable {
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;
    @ApiModelProperty(value = "页数", required = true)
    private Integer pageSize;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段", required = true)
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    @ApiModelProperty(value = "排序顺序（默认升序）", required = true)
    private String sortOrder = SORT_ORDER_ASC;

    private static final long serialVersionUID = 1L;
}
