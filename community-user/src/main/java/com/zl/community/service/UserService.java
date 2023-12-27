package com.zl.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zl.community.model.dto.OperateByIdModel;
import com.zl.community.model.dto.OperateByUserNameOrPhoneOrEmailModel;
import com.zl.community.model.dto.PageQueryModel;
import com.zl.community.model.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zl.community.model.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
* @Author : ZL
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-12-07 10:10:19
*/
@Service
public interface UserService extends IService<UserEntity> {
    /**
     * 通过用户名or邮箱或电话查询用户
     *
     * @param operateByUserNameOrPhoneOrEmailModel 用户名or邮箱或电话查询用户
     * @return 用户对象信息
     */
    Optional<UserEntity> selectByUserNameOrEmailOrPhone(OperateByUserNameOrPhoneOrEmailModel operateByUserNameOrPhoneOrEmailModel);

    /**
     * 根据id软删除系统用户
     * @param operateByIdModel  删除系统用户id
     */
    void deleteSysUser(OperateByIdModel operateByIdModel);

    /**
     * 修改系统用户信息
     * @param user
     */
    void updateSysUser(UserEntity user);

    /**
     * 根据id查询单个系统用户
     * @param operateByIdModel
     * @return
     */
    Optional<UserEntity> queryById(OperateByIdModel operateByIdModel);

    /**
     * 分页查询系统用户
     * @param pageQueryModel
     * @return
     */
    IPage<UserEntity> pageQuery(PageQueryModel pageQueryModel);

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    UserVO getUserVO(UserEntity user);

    /**
     * 根据用户名获取用户信息
     *
     * @param userAccount
     * @return
     */
    UserEntity getUserEntity(String userAccount);

    /**
     * 关联查询用户信息在主页查询帖子时
     * @param userAccount
     * @return
     */
    List<UserEntity>  getUserListByUserAccount(Set<String> userAccount);
}
