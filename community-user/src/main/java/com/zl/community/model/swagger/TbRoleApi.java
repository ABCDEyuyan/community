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
 * 角色
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_role")
@ApiModel(value = "TbRoleApi对象", description = "角色")
public class TbRoleApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("角色名")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("是否已删除 0-未删除 1-软删除")
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新者(账号)")
    private String updateUser;

}