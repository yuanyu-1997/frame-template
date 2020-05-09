package cn.yuanyu.mp.mapper;

import cn.yuanyu.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试扩展方法是否可行
     * select * from tb_user
     */
    @Test
    public void testFindAll() {
        List<User> users = userMapper.findAll();
        users.forEach(System.out::println);
    }


    /**
     * 1.根据id查询（自定义的方法）
     * select * from tb_user where id = ?
     */
    @Test
    public void testFindById() {
        System.out.println(userMapper.findById(1L));
    }

    /**
     * 2.根据id查询一条记录
     * SELECT id,user_name,password,name,age,email FROM tb_user WHERE id=?
     */
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(2L);
        System.out.println(user);
    }

}