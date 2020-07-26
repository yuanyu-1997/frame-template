

package io.renren.modules.sys.controller;

import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;

/**
 * Controller公共组件
 *
 * @author yuanyu
 */
public abstract class AbstractController {
    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }
    protected Long getUserId() {
        return getUser().getUserId();
    }
}
