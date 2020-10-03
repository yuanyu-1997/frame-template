package cn.yuanyu.app.mapper;

import cn.yuanyu.app.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User getUserByUserId(@Param("userId") Integer userId);
    
} 