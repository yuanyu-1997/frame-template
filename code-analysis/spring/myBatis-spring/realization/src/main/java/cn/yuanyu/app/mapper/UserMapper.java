package cn.yuanyu.app.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{userId}")
    List<Map<String, String>> getUserByUserId(@Param("userId") Integer userId);
    
} 