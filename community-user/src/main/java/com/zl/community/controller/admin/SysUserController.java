package com.zl.community.controller.admin;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zl.community.annotation.LogOperation;
import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.exception.BusinessException;
import com.zl.community.model.dto.OperateByIdModel;
import com.zl.community.model.dto.OperateByUserNameOrPhoneOrEmailModel;
import com.zl.community.model.dto.PageQueryModel;
import com.zl.community.model.entity.UserEntity;
import com.zl.community.model.swagger.UserApi;
import com.zl.community.service.UserService;
import com.zl.community.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @Author : ZL
 */
@RestController
@RequestMapping("/manage/user")
@Api(tags = "系统管理-系统用户管理")
@Slf4j
@RequiredArgsConstructor
public class SysUserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SnowflakeGenerator snowflakeGenerator;

    /**
     * 添加系统用户
     * @param userApi
     * @return
     */
    @LogOperation("添加系统用户操作")
    @PostMapping("/insertSysUser")
    @ApiOperation("添加系统用户")
    public BaseResponse<String> insertSysUser(@RequestBody @Valid UserApi userApi){
        UserEntity sysUser = new UserEntity();
        BeanUtils.copyProperties(userApi,sysUser);
        sysUser.setUserAccount(snowflakeGenerator.next().toString());
        // 保存密码使用bCryptPasswordEncoder加密
        sysUser.setUserPassword(bCryptPasswordEncoder.encode(userApi.getUserPassword()));
        Assert.isTrue(userService.save(sysUser),"添加失败");
        return ResultUtils.success(ResultCode.SUCCESS,null);
    }

    @LogOperation("删除系统用户 软删除")
    @PostMapping("/deleteSysUser")
    @ApiOperation("删除系统用户 软删除")
    public BaseResponse deleteSysUser(@RequestBody @Valid OperateByIdModel operaModelById){
        userService.deleteSysUser(operaModelById);
        return ResultUtils.success(ResultCode.SUCCESS,null);
    }

    @LogOperation("修改系统用户信息")
    @PostMapping("/updateSysUser")
    @ApiOperation("修改系统用户信息")
    public BaseResponse updateSysUser(@RequestBody @Valid UserApi userApi){
        UserEntity sysUser = new UserEntity();
        BeanUtils.copyProperties(userApi,sysUser);
        userService.updateSysUser(sysUser);
        return ResultUtils.success(ResultCode.SUCCESS,null);
    }

    @LogOperation("分页查询用户信息")
    @PostMapping("/selectSysUserPage")
    @ApiOperation("分页查询用户信息")
    public BaseResponse selectSysUserPage(@RequestBody @Valid PageQueryModel model){
        IPage<UserEntity> userEntityIPage = userService.pageQuery(model);
        IPage<UserApi> page = new Page<>();
        BeanUtils.copyProperties(userEntityIPage,page);
        return ResultUtils.success(ResultCode.SUCCESS,page);
    }

    @LogOperation("根据ID查询用户信息")
    @PostMapping("/selectSysUserById")
    @ApiOperation("根据ID查询用户信息")
    public BaseResponse selectSysUserById(@RequestBody @Valid OperateByIdModel operateByIdModel){
        Optional<UserEntity> user = userService.queryById(operateByIdModel);
        if (user.isPresent()) {
            return ResultUtils.success(ResultCode.SUCCESS,user.get());
        } else {
            throw new BusinessException(ResultCode.DATA_ISEMPTY);
        }
    }

    @LogOperation("通过用户名or邮箱or电话查询用户")
    @PostMapping("/selectSysUserByUserNameOrEmailOrPhone")
    @ApiOperation("通过用户名or邮箱or电话查询用户")
    /**
     *  Optional 类型，它可以用来解决 null 值的问题，避免空指针异常。
     *  Optional<UserEntity> 表示 UserEntity 类型的实例对象可能存在，也可能不存在，因此 Optional<UserEntity> 可以为空，也可以包含一个 UserEntity 对象。
     * 在前端得到 "present": true 的情况下，说明后端成功返回了一个 Optional<UserEntity> 对象，
     * 并且该对象中包含一个 UserEntity 实例，即 UserEntity 不为 null。如果 Optional<UserEntity> 为空，前端会得到 "present": false 的结果。
     * 因此，前端可以根据 "present" 的值来判断 Optional<UserEntity> 是否为空，如果不为空，则可以通过
     * .get() 方法获取到 UserEntity 实例对象。例如，若后端返回的 Optional<UserEntity> 对象名为 userOpt，则可以使用以下代码获取 UserEntity 对象：
     */
    public BaseResponse selectSysUserById(@RequestBody @Valid OperateByUserNameOrPhoneOrEmailModel operateByUserNameOrPhoneOrEmailModel){
        Optional<UserEntity> userEntity = userService.selectByUserNameOrEmailOrPhone(operateByUserNameOrPhoneOrEmailModel);
        System.out.println("user = " + userEntity);
        if (userEntity.isPresent()) {
            return ResultUtils.success(ResultCode.SUCCESS,userEntity.get());
        } else {
            throw new BusinessException(ResultCode.DATA_ISEMPTY);
        }

    }
}
