package com.yuanyu.mp.simple.mapper;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.yuanyu.mp.simple.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserMapperTest {

    /**
     * MyBatis
     */
    @Test
    public void testMyBatisFindAll() throws Exception {
        String config = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        //
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //测试查询
        List<User> users = userMapper.findAll();
        users.forEach(System.out::println);
        //TODO 不能调用plus接口里面的方法
        //userMapper.selectList(null).forEach(System.out::println);
    }


    /**
     * MyBatisPlus
     */
    @Test
    public void testMyBatisPlusFindAll() throws Exception {
        String config = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        //TODO MybatisSqlSessionFactoryBuilder 是plus里面的
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //测试查询
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        //
        userMapper.findAll().forEach(System.out::println);
    }


}