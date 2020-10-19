package cn.yuanyu.app.mapper.sys;


import cn.yuanyu.app.entity.sys.SysPermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 权限
 *
 * @author yuanyu
 */
public interface SysPermissionMapper extends BaseMapper<SysPermissionEntity> {
    /**
     * 通过权限标识符查询权限
     */
    SysPermissionEntity selectByPermTag(String permTag);
}
