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


    /**
     * <select id="getEmployeeById" parameterType="int" resultType="cn.yuanyu.mybatis.domain.Employee">
     *     SELECT *
     *     FROM tb_employee
     *     where id = #{id}
     * </select>
     */
    /**
     * <select id="getEmployeeById" parameterType="int" resultType="cn.yuanyu.mybatis.domain.Employee" useCache="false">
     *     SELECT *
     *     FROM tb_employee
     *     where id = #{id}
     * </select>
     */
    @Test
    public void testCache() {
        //第一个会话
        SqlSession firstSqlSession = sqlSessionFactory.openSession();
        EmployeeMapper firstEmpMapper = firstSqlSession.getMapper(EmployeeMapper.class);
        //Cache Hit Ratio [com.nobug.mapper.EmployeeMapper]: 0.0
        System.out.println(firstEmpMapper.getEmployeeById(1));
        //第一个SqlSession需要关闭
        firstSqlSession.close();
        //第二个会话
        SqlSession secondSession = sqlSessionFactory.openSession();
        EmployeeMapper secondMapper = secondSession.getMapper(EmployeeMapper.class);
        //Cache Hit Ratio [com.nobug.mapper.EmployeeMapper]: 0.5
        System.out.println(secondMapper.getEmployeeById(1));
        //Cache Hit Ratio [com.nobug.mapper.EmployeeMapper]: 0.6666666666666666
        System.out.println(secondMapper.getEmployeeById(1));
    }


}