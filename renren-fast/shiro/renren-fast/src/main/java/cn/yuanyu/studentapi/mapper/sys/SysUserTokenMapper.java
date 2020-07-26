

package cn.yuanyu.studentapi.mapper.sys;


import cn.yuanyu.studentapi.entity.sys.SysUserTokenEntity;
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
