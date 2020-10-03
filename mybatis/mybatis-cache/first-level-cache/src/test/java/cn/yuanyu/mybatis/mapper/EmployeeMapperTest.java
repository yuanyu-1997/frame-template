package cn.yuanyu.mybatis.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


public class EmployeeMapperTest {

    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void initSqlSessionFactory() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void testCache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        System.out.println(mapper.getEmployeeById(1));
        System.out.println(mapper.getEmployeeById(1));
    }





}