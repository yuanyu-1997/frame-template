package cn.baker.mybatis.mapper;


import cn.baker.mybatis.entity.User;

/**
 * @author yuanyu
 */
public interface UserMapper {
    User selectUser(User user);
    User selectUserByUsername(String username);
}
