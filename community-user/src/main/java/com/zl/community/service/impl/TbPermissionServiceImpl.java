package com.zl.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.community.model.entity.PermissionEntity;
import com.zl.community.service.TbPermissionService;
import com.zl.community.mapper.TbPermissionMapper;
import org.springframework.stereotype.Service;

/**
* @Author : ZL
* @description 针对表【tb_permission(权限)】的数据库操作Service实现
* @createDate 2023-12-07 10:19:02
*/
@Service
public class TbPermissionServiceImpl extends ServiceImpl<TbPermissionMapper, PermissionEntity>
    implements TbPermissionService{

}




