package com.zl.community.service.impl;

import com.zl.community.common.ResultCode;
import com.zl.community.exception.BusinessException;
import com.zl.community.service.MinioService;
import com.zl.community.util.MinioUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : ZL
 */
@Service
public class MinioServiceImpl implements MinioService {

    @Override
    public boolean avatarUpload(MultipartFile file) {
        if (file == null) {
            throw new BusinessException(ResultCode.UPLOAD_ERROR);
        }
        try {
            MinioUtils.putObject(file);
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
        String fileUrl = MinioUtils.getObjectUrl(fileName);
        return fileUrl;
    }
}
