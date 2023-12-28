package com.zl.community.controller.user;

import cn.hutool.core.io.FileUtil;
import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.enums.FileUploadBizEnum;
import com.zl.community.exception.BusinessException;
import com.zl.community.model.dto.UploadFileModel;
import com.zl.community.util.MinioUtils;
import com.zl.community.util.ResponseUtil;
import com.zl.community.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

/**
 * @Author : ZL
 * 文件上传接口
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    /**
     * 文件上传接口
     * @param multipartFile
     * @param uploadFileModel
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(@RequestPart("file")MultipartFile multipartFile,
                                           UploadFileModel uploadFileModel){
        String biz = uploadFileModel.getBiz();
        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
        if (fileUploadBizEnum == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        validFile(multipartFile, fileUploadBizEnum);
        try {
            // 上传文件
            MinioUtils.putObject(multipartFile);
            // 返回可访问地址
            return ResultUtils.success(MinioUtils.getObjectUrl(multipartFile.getOriginalFilename()));
        } catch (Exception e) {
            log.error("file upload error, fileName = " + multipartFile.getOriginalFilename(), e);
            throw new BusinessException(ResultCode.SYSTEM_ERROR, "上传失败");
        }
    }

    /**
     * 校验文件
     *
     * @param multipartFile
     * @param fileUploadBizEnum 业务类型
     */
    private void validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum) {
        // 文件大小
        long fileSize = multipartFile.getSize();
        // 文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        final long ONE_M = 1024 * 1024L;
        if (FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (fileSize > ONE_M) {
                throw new BusinessException(ResultCode.PARAMS_ERROR, "文件大小不能超过 1M");
            }
            if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(fileSuffix)) {
                throw new BusinessException(ResultCode.PARAMS_ERROR, "文件类型错误");
            }
        }
    }
}
