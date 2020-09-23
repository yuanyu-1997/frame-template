package cn.yuanyu.mybatis.mapper;


import cn.yuanyu.mybatis.entity.Person;

/**
 * @author yuanyu
 */
public interface PersonMapper {

    /**
     * 通过用户名查找用户
     * @param username 用户名（唯一）
     * @return 用户
     */
    Person selectPersonByUsername(String username);


}
