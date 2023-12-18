package com.zl.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.community.model.entity.UserEntity;
import com.zl.community.service.UserService;
import com.zl.community.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @Author : ZL
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-12-07 10:10:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserService{

}




