package com.zl.community.mapper;

import com.zl.community.model.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : ZL
 * @description 针对表【tb_role(角色)】的数据库操作Mapper
 * @createDate 2023-12-07 10:17:37
 * @Entity com.zl.community.model.entity.TbRole
 */
public interface TbRoleMapper extends BaseMapper<RoleEntity> {
    /**
     * 根据用户id查询角色列表
     * @param userId
     * @return RoleEntity角色列表
     */
    List<RoleEntity> getUserRolesByUserId(@Param("userId") Long userId);
}




