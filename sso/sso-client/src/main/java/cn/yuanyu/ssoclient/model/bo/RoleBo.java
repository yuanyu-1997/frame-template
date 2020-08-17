package cn.yuanyu.ssoclient.model.bo;

import cn.yuanyu.ssoclient.model.Func;
import cn.yuanyu.ssoclient.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 扩展角色类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleBo extends Role {
    /**
     * 用户所属的角色信息
     */
    private Set<Func> funcs;
}
