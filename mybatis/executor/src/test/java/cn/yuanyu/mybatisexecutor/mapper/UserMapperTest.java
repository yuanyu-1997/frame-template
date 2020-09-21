package cn.yuanyu.mybatisexecutor.mapper;

import cn.yuanyu.mybatisexecutor.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    @BeforeClass
    public static void initSqlSessionFactory() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    // 清空表中数据, 同时重置自增序列从0开始
    public void clearTable() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.getMapper(UserMapper.class).clear();
    }

    // 验证环境是否ok
    @Test
    public void testEnvIsOk() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.insert(new User(1, "蔡徐坤", "男", new Date(), "重庆万州", "123456"));
        System.out.println(res);
        System.out.println(mapper.queryById(1));
        mapper.clear();
    }

    /**
     * @param list 插入的User集合
     * @param type ExecutorType
     * @param autoCommit 是否自动提交
     */
    public void testSave(List<User> list, ExecutorType type, boolean autoCommit) {
        clearTable();
        SqlSession sqlSession = sqlSessionFactory.openSession(type, autoCommit);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        long start = System.currentTimeMillis();
        try {
            list.stream().forEach((user -> mapper.insert(user)));
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
        }
        long end = System.currentTimeMillis();
        clearTable();
        System.out.println("耗时: " + (end - start) + "(ms)");
    }

    @Test
    public void test_() {
        // 初始化10000个对象
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(new User(i, i + "", "男", new Date(), "重庆万州", "123456"));
        }
        //测试
        System.out.println("BATCH");
        testSave(list, ExecutorType.BATCH, false);

        System.out.println("SIMPLE");
        testSave(list, ExecutorType.SIMPLE, false);

        System.out.println("REUSE");
        testSave(list, ExecutorType.REUSE, false);



    }

}
