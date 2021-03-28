package cn.baker.mybatis.mapper;

import cn.baker.mybatis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;


    @Test
    public void selectUser() {
        User user = User.builder()
                .username("lisi")
                .password("123456")
                .age((byte)20)
                .build();
        User res = userMapper.selectUser(user);

        System.out.println("user => " + res);
    }


    @Test
    public void selectUserByUsername() {
        User res = userMapper.selectUserByUsername("lisi");

        System.out.println("user => " + res);
    }
}