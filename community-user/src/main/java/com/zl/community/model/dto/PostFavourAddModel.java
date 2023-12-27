package com.zl.community.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 帖子收藏/取消收藏模型
 * @Author : ZL
 */
@Data
public class PostFavourAddModel implements Serializable {
    /**
     * 帖子 id
     */
    private Long postId;

    private static final long serialVersionUID = 1L;
}
