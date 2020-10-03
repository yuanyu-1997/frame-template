package cn.yuanyu.app.mapper;


import cn.yuanyu.app.bean.User;
import cn.yuanyu.app.config.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserMapperTest {

    @Test
    public void getUserByUserId() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        User res = userMapper.getUserByUserId(1);
        System.out.println("res => " + res);
    }

}