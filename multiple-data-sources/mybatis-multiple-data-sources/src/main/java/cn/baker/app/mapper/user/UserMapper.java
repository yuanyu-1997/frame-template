package cn.baker.app.mapper.user;


import cn.baker.app.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author yuanyu
 */
public interface UserMapper {
    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return User
     */
    User selectUserByUsername(@Param("username") String username);
}
