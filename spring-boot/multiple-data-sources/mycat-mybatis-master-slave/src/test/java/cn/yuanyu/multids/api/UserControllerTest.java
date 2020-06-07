package cn.yuanyu.multids.api;

import cn.yuanyu.multids.entity.User;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;


public class UserControllerTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void selectOne() {
        String url = "http://localhost:6969/user/6";
    }

    @Test
    public void insert(){
        String url = "http://localhost:6969/user";
        User user = new User("罗志祥","123456","小猪",18,"motion@bug.cn");
        User res = restTemplate.postForObject(url, user, User.class);
        System.out.println(res);
    }
}