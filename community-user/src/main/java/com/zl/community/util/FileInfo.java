package com.zl.community.util;

import lombok.Data;
import lombok.ToString;

import java.io.InputStream;

/**
 * @Author : ZL
 */
@Data
@ToString
public class FileInfo {
    /**
     * 输入流
     */
    private InputStream inputStream;
    /**
     * 上传文件名
     */
    private String originalFilename;
    /**
     * 文件名+雪花Id生成新文件名传入minio服务区
     */
    private String newFilename;
    /**
     * 文件大小
     */
    private long size;
    /**
     * 文件类型
     */
    private String contentType;
}