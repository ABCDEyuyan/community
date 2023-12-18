package com.zl.community.model.swagger;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author : ZL
 * 角色权限关系
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_role_permission")
@ApiModel(value = "TbRolePermissionApi对象", description = "角色权限关系")
public class TbRolePermissionApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色主键")
    private Long roleId;

    @ApiModelProperty("权限主键")
    private Long permissionId;

}