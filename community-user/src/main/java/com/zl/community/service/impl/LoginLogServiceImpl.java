package com.zl.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.community.model.dto.PageQueryModel;
import com.zl.community.model.entity.LoginLogEntity;
import com.zl.community.service.LoginLogService;
import com.zl.community.mapper.LoginLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.zl.community.constant.Constants.UserConstant.ENABLE;

/**
 * @Author : ZL
 * @description 针对表【login_log(登录日志表)】的数据库操作Service实现
 * @createDate 2023-12-13 11:29:06
 */
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLogEntity>
        implements LoginLogService {

    private final LoginLogMapper mapper;

    @Override
    public IPage<LoginLogEntity> pageQuery(PageQueryModel pageQueryModel) {
        IPage<LoginLogEntity> page = new Page<>(pageQueryModel.getPageNo(), pageQueryModel.getPageSize());
        LambdaQueryWrapper<LoginLogEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LoginLogEntity::getStatus, ENABLE);
        IPage<LoginLogEntity> iPage = mapper.selectPage(page, queryWrapper);
        Assert.isTrue(iPage.getTotal() > 0, "当前无数据");
        return iPage;
    }
}




