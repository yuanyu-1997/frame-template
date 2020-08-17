package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(@Param("username") String username);
}