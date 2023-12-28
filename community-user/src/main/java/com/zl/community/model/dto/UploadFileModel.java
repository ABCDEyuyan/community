package com.zl.community.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : ZL
 * 文件上传请求
 */
@Data
public class UploadFileModel implements Serializable {

    /**
     * 业务
     */
    private String biz;

    private static final long serialVersionUID = 1L;
}
