package com.zl.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zl.community.model.dto.PageQueryModel;
import com.zl.community.model.entity.LoginLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author : ZL
 * @description 针对表【login_log(登录日志表)】的数据库操作Service
 * @createDate 2023-12-13 11:29:06
 */
public interface LoginLogService extends IService<LoginLogEntity> {
    /**
     * 分页查询登录日志
     */
    IPage<LoginLogEntity> pageQuery(PageQueryModel pageQueryModel);
}
