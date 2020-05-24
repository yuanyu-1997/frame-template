package cn.yuanyu.multids.api;

import cn.yuanyu.multids.entity.User;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class UserControllerTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void insert() {
        String url = "http://localhost:6969/user";
        User user = new User(284950495, "乔碧萝", "女", new Date(), "北京", "123456");
        User res = restTemplate.postForObject(url, user, User.class);
        System.out.println(res);
    }

    @Test
    public void selectOne() {
        String url = "http://localhost:6969/user/284950495";
        User res = restTemplate.getForObject(url, User.class);
        System.out.println(res);
    }
}