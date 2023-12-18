package com.zl.community.model.swagger;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author : ZL
 * 用户角色关系
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user_role")
@ApiModel(value = "TbUserRoleApi对象", description = "用户角色关系")
public class TbUserRoleApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户主键")
    private Long userId;

    @ApiModelProperty("角色主键")
    private Long roleId;

}