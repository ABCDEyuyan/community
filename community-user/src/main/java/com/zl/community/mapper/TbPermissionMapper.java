package com.zl.community.mapper;

import com.zl.community.model.entity.PermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : ZL
 * @description 针对表【tb_permission(权限)】的数据库操作Mapper
 * @createDate 2023-12-07 10:19:02
 * @Entity com.zl.community.model.entity.TbPermission
 */
public interface TbPermissionMapper extends BaseMapper<PermissionEntity> {
    /**
     * 根据角色id查询权限列表
     * @param roleIds
     * @return  PermissionEntity权限列表
     */
    List<PermissionEntity> getDistinctPermissionsByRoleIds(@Param("roleIds") List<Long> roleIds);

}




