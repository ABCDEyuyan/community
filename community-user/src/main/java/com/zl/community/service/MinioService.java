package com.zl.community.service;

import com.zl.community.util.FileInfo;

/**
 * @Author : ZL
 * Minio实现类
 */
public interface MinioService {
    /**
     * Minio图片上传
     * @param file
     * @return
     */
    boolean avatarUpload(FileInfo file);

    /**
     * 获取Minio图片地址
     * @param fileName
     * @return
     */
    String getAvatarUrl(String fileName);
}
