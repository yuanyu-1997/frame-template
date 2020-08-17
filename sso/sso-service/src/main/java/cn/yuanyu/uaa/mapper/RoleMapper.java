package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends BaseMapper<Role> {
    Role selectByRoleName(@Param("roleName") String roleName);
}