package com.zl.community.controller.user;

import cn.hutool.core.io.FileUtil;
import com.zl.community.annotation.LogOperation;
import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.config.SnowflakeConfig;
import com.zl.community.enums.FileUploadBizEnum;
import com.zl.community.exception.BusinessException;
import com.zl.community.model.dto.UploadFileModel;
import com.zl.community.util.FileInfo;
import com.zl.community.util.FileUtils;
import com.zl.community.util.MinioUtils;
import com.zl.community.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author : ZL
 * 文件上传接口
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件上传接口")
@Slf4j
@RequiredArgsConstructor
public class FileController {
    private final MinioUtils minioUtils;
    private final SnowflakeConfig snowflakeConfig;

    /**
     * 文件上传接口
     *
     * @param multipartFile
     * @param uploadFileModel
     * @return
     */
    @LogOperation("文件上传操作")
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile multipartFile,
                                           UploadFileModel uploadFileModel) throws IOException {
        String biz = uploadFileModel.getBiz();
        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
        if (fileUploadBizEnum == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        validFile(multipartFile, fileUploadBizEnum);
        FileInfo fileInfo=new FileInfo();
        BeanUtils.copyProperties(multipartFile, fileInfo);
        fileInfo.setInputStream(multipartFile.getInputStream());
        fileInfo.setNewFilename(FileUtils.generateNewFilename(fileInfo.getOriginalFilename(), snowflakeConfig));
        try {
            // 上传文件
            minioUtils.putObject(fileInfo);
            // 返回可访问地址
            return ResultUtils.success(minioUtils.getObjectUrl(fileInfo.getNewFilename()));

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
        final long ONE_M = 10240 * 10240L;
        if (FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (fileSize > ONE_M) {
                throw new BusinessException(ResultCode.PARAMS_ERROR, "文件大小不能超过 100M");
            }
            if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(fileSuffix)) {
                throw new BusinessException(ResultCode.PARAMS_ERROR, "文件类型错误");
            }
        }
    }
}
