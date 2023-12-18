package com.zl.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.community.model.entity.RolePermissionEntity;
import com.zl.community.service.TbRolePermissionService;
import com.zl.community.mapper.TbRolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @Author : ZL
* @description 针对表【tb_role_permission(角色权限关系)】的数据库操作Service实现
* @createDate 2023-12-07 10:24:58
*/
@Service
public class TbRolePermissionServiceImpl extends ServiceImpl<TbRolePermissionMapper, RolePermissionEntity>
    implements TbRolePermissionService{

}




