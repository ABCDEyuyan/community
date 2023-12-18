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
 * 权限
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_permission")
@ApiModel(value = "TbPermissionApi对象", description = "权限")
public class TbPermissionApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("权限名")
    private String name;

    @ApiModelProperty("类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址")
    private String url;

    @ApiModelProperty("权限类型，页面-1，按钮-2")
    private Integer type;

    @ApiModelProperty("权限表达式")
    private String permission;

    @ApiModelProperty("后端接口访问方式")
    private String method;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("是否已删除 0-未删除 1-软删除")
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新者(账号)")
    private String updateUser;

    @ApiModelProperty("父级id")
    private Long parentId;

}