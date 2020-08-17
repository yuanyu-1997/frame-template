package cn.yuanyu.ssoclient.model.bo;

import cn.yuanyu.ssoclient.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 扩展用户类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBo extends User {
    /**
     * 用户所属的角色信息
     */
    private Set<RoleBo> roles;

}
