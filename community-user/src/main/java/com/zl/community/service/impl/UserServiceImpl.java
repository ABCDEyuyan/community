package com.zl.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.community.model.dto.OperateByIdModel;
import com.zl.community.model.dto.OperateByUserNameOrPhoneOrEmailModel;
import com.zl.community.model.dto.PageQueryModel;
import com.zl.community.model.entity.UserEntity;
import com.zl.community.model.vo.UserVO;
import com.zl.community.service.UserService;
import com.zl.community.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.zl.community.constant.Constants.UserConstant.DISABLE;
import static com.zl.community.constant.Constants.UserConstant.ENABLE;

/**
* @Author : ZL
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-12-07 10:10:19
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserService{
    private final UserMapper userMapper;

    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名or邮箱or手机号
     * @return 用户信息
     */
    @Override
    public Optional<UserEntity> selectByUserNameOrEmailOrPhone(OperateByUserNameOrPhoneOrEmailModel username) {
        return userMapper.selectByUserNameOrEmailOrPhone(username.getNameOrPhoneOrEmail(),username.getNameOrPhoneOrEmail(),username.getNameOrPhoneOrEmail());
    }

    /**
     * 根据id软删除系统用户
     * @param operateByIdModel  删除系统用户id
     */
    @Override
    public void deleteSysUser(OperateByIdModel operateByIdModel) {
        LambdaUpdateWrapper<UserEntity> userEntityLambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        userEntityLambdaUpdateWrapper.eq(UserEntity::getId, operateByIdModel.getId())
                .set(UserEntity::getIsDeleted,DISABLE);
        Assert.isTrue(this.update(userEntityLambdaUpdateWrapper),"删除失败");
    }

    /**
     * 修改系统用户信息
     * @param user
     */
    @Override
    public void updateSysUser(UserEntity user) {
        LambdaUpdateWrapper<UserEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserEntity::getIsDeleted,ENABLE)
                .eq(UserEntity::getId,user.getId());
        Assert.isTrue(this.update(user,updateWrapper),"修改失败");
    }

    /**
     * 根据id查询单个系统用户
     * @param OperateByIdModel
     * @return
     */
    @Override
    public  Optional<UserEntity> queryById(OperateByIdModel OperateByIdModel) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getId, OperateByIdModel.getId());
        Optional<UserEntity> user = Optional.ofNullable(userMapper.selectOne(queryWrapper));
        Assert.notNull(user,"当前无数据");
        return user;
    }

    /**
     * 分页查询系统用户
     * @param pageQueryModel
     * @return
     */
    @Override
    public IPage<UserEntity> pageQuery(PageQueryModel pageQueryModel) {
        LambdaQueryWrapper<UserEntity> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getIsDeleted,ENABLE);
        IPage<UserEntity> iPage = new Page<>(pageQueryModel.getPageNo(), pageQueryModel.getPageSize());
        IPage<UserEntity> userEntityIPage = userMapper.selectPage(iPage,queryWrapper);
        Assert.isTrue(userEntityIPage.getTotal()>0,"当前无数据");
        return userEntityIPage;
    }

    /**
     * 获取用户信息（脱敏）
     * @param user
     * @return
     */
    @Override
    public UserVO getUserVO(UserEntity user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public UserEntity getUserEntity(String userAccount) {
        LambdaQueryWrapper<UserEntity> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUserAccount,userAccount);
        UserEntity user = userMapper.selectOne(queryWrapper);
        Assert.notNull(user,"当前无数据");
        return user;
    }

    @Override
    public List<UserEntity> getUserListByUserAccount(Set<String> userAccount) {
        // 将 Set 转换为数组
        String[] userAccountArray = userAccount.toArray(new String[0]);
        LambdaQueryWrapper<UserEntity> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.in(UserEntity::getUserAccount, userAccountArray);
        List<UserEntity> userEntities = userMapper.selectList(queryWrapper);
        Assert.notNull(userEntities,"当前无数据");
        return userEntities;
    }
}




