package cn.yuanyu.uaa.model.bo;

import cn.yuanyu.uaa.model.Func;
import cn.yuanyu.uaa.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 扩展角色类
 */
// https://blog.csdn.net/qq_15071263/article/details/91660519
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleBo extends Role{
    /**
     * 用户所属的角色信息
     */
    private Set<Func> funcs;
}
