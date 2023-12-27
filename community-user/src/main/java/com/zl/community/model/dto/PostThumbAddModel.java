package com.zl.community.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : ZL
 * 帖子点赞请求模型
 *
 */
@Data
public class PostThumbAddModel implements Serializable {
    /**
     * 帖子 id
     */
    private Long postId;

    private static final long serialVersionUID = 1L;
}
