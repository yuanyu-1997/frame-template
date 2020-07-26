

package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysUserTokenEntity;

/**
 * 系统用户Token
 *
 * @author yuanyu
 */
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);

}
