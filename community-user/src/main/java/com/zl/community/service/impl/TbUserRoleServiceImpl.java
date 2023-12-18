package com.zl.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.community.model.entity.UserRoleEntity;
import com.zl.community.service.TbUserRoleService;
import com.zl.community.mapper.TbUserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @Author : ZL
* @description 针对表【tb_user_role(用户角色关系)】的数据库操作Service实现
* @createDate 2023-12-07 10:25:41
*/
@Service
public class TbUserRoleServiceImpl extends ServiceImpl<TbUserRoleMapper, UserRoleEntity>
    implements TbUserRoleService{

}




