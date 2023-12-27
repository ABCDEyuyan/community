package com.zl.community.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : ZL
 * 分页查询帖子模型
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostQueryModel extends PageQueryModel implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long notId;

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 至少有一个标签
     */
    private List<String> orTags;

    /**
     * 创建用户 账号
     */
    private String userAccount;

    /**
     * 收藏用户 账号
     */
    private String favourUserAccount;

    private static final long serialVersionUID = 1L;
}
