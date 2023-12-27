package com.zl.community.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : ZL
 */
@Data
public class PostEditModel implements Serializable {
    /**
     * id
     */
    private Long id;

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

    private static final long serialVersionUID = 1L;
}
