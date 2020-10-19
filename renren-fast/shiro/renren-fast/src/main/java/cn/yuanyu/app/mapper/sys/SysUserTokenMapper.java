

package cn.yuanyu.app.mapper.sys;


import cn.yuanyu.app.entity.sys.SysUserTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 系统用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */

public interface SysUserTokenMapper extends BaseMapper<SysUserTokenEntity> {
    /**
     * 通过token查询一条记录
     *
     * @param token token
     * @return SysUserTokenEntity
     */
    SysUserTokenEntity queryByToken(String token);
}
