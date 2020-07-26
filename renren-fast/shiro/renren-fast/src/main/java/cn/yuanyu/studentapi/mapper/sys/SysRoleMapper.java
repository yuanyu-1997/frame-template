package cn.yuanyu.studentapi.mapper.sys;


import cn.yuanyu.studentapi.entity.sys.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 操作角色
 *
 * @author yuanyu
 */
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
    /**
     * 通过角色名查找角色
     *
     * @param roleName 角色名（唯一）
     */
    SysRoleEntity selectByRoleName(String roleName);
}
