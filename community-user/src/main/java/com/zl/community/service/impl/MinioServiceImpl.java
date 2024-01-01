package com.zl.community.service.impl;

import com.zl.community.common.ResultCode;
import com.zl.community.exception.BusinessException;
import com.zl.community.service.MinioService;
import com.zl.community.util.FileInfo;
import com.zl.community.util.MinioUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author : ZL
 */
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    private final MinioUtils minioUtils;
    @Override
    public boolean avatarUpload(FileInfo file) {
        if (file == null) {
            throw new BusinessException(ResultCode.UPLOAD_ERROR);
        }
        try {
            minioUtils.putObject(file);
            return true;
        } catch (Exception e) {
            throw new BusinessException(ResultCode.UPLOAD_ERROR);
        }
    }

    @Override
    public String getAvatarUrl(String fileName) {
        if(fileName==null){
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        String fileUrl = minioUtils.getObjectUrl(fileName);
        return fileUrl;
    }
}
