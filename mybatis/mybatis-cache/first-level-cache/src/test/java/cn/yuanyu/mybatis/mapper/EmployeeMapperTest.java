package cn.yuanyu.mybatis.mapper;

import cn.yuanyu.mybatis.domain.Employee;
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

    /**
     * 一级缓存代码演示
     */

    @Test
    public void testCache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        System.out.println(mapper.getEmployeeById(1));
        System.out.println(mapper.getEmployeeById(1));
    }


    /**
     * 1、同一个方法，不同的参数，由于可能之前没查询过，所有还会发新的sql
     */

    @Test
    public void testCache_1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //发送SQL
        System.out.println(mapper.getEmployeeById(1));
        //2.sqlSession相同，查询条件不同
        //条件不同 再次发送SQL
        System.out.println(mapper.getEmployeeById(2));
    }


    /**
     * 2、只有在同一个SqlSession期间查询到的数据会保存在这个SqlSession的缓存中，下次使用这个SqlSession查询会从缓存中拿，每次查询，先看一级缓存中有没有，如果没有就去发送新的sql，每个SqlSession拥有自己的一级缓存
     */
    @Test
    public void testCache_2() {
        //第一个会话
        SqlSession firstSqlSession = sqlSessionFactory.openSession();
        //发送SQL
        EmployeeMapper firstEmpMapper = firstSqlSession.getMapper(EmployeeMapper.class);
        System.out.println(firstEmpMapper.getEmployeeById(1));

        //第二个会话
        //1.查询条件相同但是，不是同一个SqlSession一级缓存失效
        //新的SqlSession发送SQL
        SqlSession secondSession = sqlSessionFactory.openSession();
        EmployeeMapper secondMapper = secondSession.getMapper(EmployeeMapper.class);
        System.out.println(secondMapper.getEmployeeById(1));
    }


    /**
     * 3、在这个SqlSession期间执行任何一次增删改操作，增删改操作会把缓存清空
     */
    @Test
    public void testCache_3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //第一次查询 发送SQL
        System.out.println(mapper.getEmployeeById(1));

        //执行任何一个增删改操作  和前面查询的记录无关
        mapper.updateEmployeeIfNecessary(Employee.builder()
                .id(2)
                .sex("男").build());

        //3.SqlSession相同查询条件也相同，但是两次查询之间执行了增删改操作(这次增删改可能对当前数据有影响)
        //第二次查询 会发送新的SQL
        System.out.println(mapper.getEmployeeById(1));
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 4、手动清空了缓存
     */
    @Test
    public void testCache_4() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        System.out.println(mapper.getEmployeeById(1));
        //4.SqlSession相同，手动清除了一级缓存（缓存清空）
        sqlSession.clearCache();
        System.out.println(mapper.getEmployeeById(1));
    }


    @Test
    public void testStart() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Employee one = sqlSession.selectOne("cn.yuanyu.mybatis.mapper.EmployeeMapper.getEmployeeById", 1);//打断点
        System.out.println(one);
        Employee two = sqlSession.selectOne("cn.yuanyu.mybatis.mapper.EmployeeMapper.getEmployeeById", 1);//打断点
        System.out.println(two);
    }

}