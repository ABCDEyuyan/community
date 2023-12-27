package com.zl.community.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : ZL
 */
@Data
@ApiModel("用户创建帖子模型")
public class PostCreateModel implements Serializable {
    @ApiModelProperty(value = "用户账号", required = true)
    String userAccount;
    @ApiModelProperty(value = "帖子标题", required = true)
    String title;
    @ApiModelProperty(value = "帖子内容", required = true)
    String content;
    @ApiModelProperty(value = "帖子说明", required = true)
    String description;
    @ApiModelProperty(value = "标签", required = true)
    List<String> tags;
    @ApiModelProperty(value = "文章种类", required = true)
    String category;
    @ApiModelProperty(value = "帖子封面", required = true)
    String avatar;
    private static final long serialVersionUID = 1L;
}
