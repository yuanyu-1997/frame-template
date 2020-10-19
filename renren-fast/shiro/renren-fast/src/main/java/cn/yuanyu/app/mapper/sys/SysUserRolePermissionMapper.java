package cn.yuanyu.app.mapper.sys;


import cn.yuanyu.app.entity.sys.SysUserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 操作用户、角色、权限的关联表
 *
 * @author yuanyu
 */
public interface SysUserRolePermissionMapper {
    /**
     * 通过用户名返回详细的用户信息（用户 角色 权限）
     *
     * @param username 用户名
     */
    SysUserEntity getUserDetailByUsername(String username);

    /**
     * 插入用户和角色关系
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    void insertUserRoleRelation(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 插入角色和权限关系
     *
     * @param roleId 角色id
     * @param permId 权限id
     */
    void insertRolePermissionRelation(@Param("roleId") String roleId, @Param("permId") String permId);
}
