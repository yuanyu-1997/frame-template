package cn.baker.app.mapper.user;

import cn.baker.app.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select(){
        User user  = userMapper.selectUserByUsername("lisi");
        System.out.println("lisi => " + user);
    }
}