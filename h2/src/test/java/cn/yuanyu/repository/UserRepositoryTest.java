package cn.yuanyu.repository;

import cn.yuanyu.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @Test
    public void testInsert() {
        //TODO 有问题
        User user = new User("蔡徐坤", "123456", "坤坤", 40, "kun@bug.cn");
        userRepository.save(user);
    }

    @Test
    public void testFindById() {
        System.out.println(userRepository.findById(1L).get());
    }

}