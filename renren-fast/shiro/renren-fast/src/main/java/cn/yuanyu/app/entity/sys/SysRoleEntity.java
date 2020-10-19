package cn.yuanyu.app.entity.sys;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Set;

/**
 * @author yuanyu
 */
@Data
@TableName("sys_role")
public class SysRoleEntity {
    /**
     * 角色ID
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色说明
     */
    private String roleDesc;
    /**
     * 角色对应权限集合
     */
    @TableField(exist = false)
    private Set<SysPermissionEntity> permissions;

    public SysRoleEntity() {
    }

    public SysRoleEntity(String id, String roleName, String roleDesc) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }
}
