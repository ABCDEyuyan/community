package com.zl.community.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author : ZL
 * 标签视图
 * TODO：想把标签改成枚举值
 */
@Data
public class TagVO implements Serializable {
    /**
     * 标签名
     */
    private String tagName;

    /**
     * 帖子使用标签次数(在service层加一个和帖子无关的操作)
     */
    private Integer postNum;

    private static final long serialVersionUID = 1L;
}
