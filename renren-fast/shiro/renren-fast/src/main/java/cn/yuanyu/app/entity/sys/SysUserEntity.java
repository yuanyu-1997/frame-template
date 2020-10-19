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
@TableName("sys_user")
public class SysUserEntity {
    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐值
     */
    private String salt;


    /**
     * 状态（0禁用、1正常）
     */
    private Integer status;


    /**
     * 用户对应的角色集合
     */
    @TableField(exist = false)
    private Set<SysRoleEntity> roles;

    public SysUserEntity() {
    }

    public SysUserEntity(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


}
