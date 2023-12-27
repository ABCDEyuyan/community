package com.zl.community.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : ZL
 */
@Data
public class PostFavourQueryModel extends PageQueryModel implements Serializable {
    /**
     * 帖子查询请求
     */
    private PostQueryModel postQueryModel;

    /**
     * 用户 账号
     */
    private String userAccount;

    private static final long serialVersionUID = 1L;
}
