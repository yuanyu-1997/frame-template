package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过用户ID和角色ID查询用户角色信息
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    UserRole selectByUserIdRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 通过角色名查询用户角色信息
     *
     * @param roleName 角色名
     */
    UserRole selectByRoleName(@Param("roleName") String roleName);

    /**
     * 通过用户ID查询用户角色信息
     *
     * @param userId 用户ID
     */
    List<UserRole> selectByUserId(@Param("userId") Integer userId);
}