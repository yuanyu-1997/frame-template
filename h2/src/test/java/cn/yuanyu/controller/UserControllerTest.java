package cn.yuanyu.controller;

import cn.yuanyu.entity.User;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class UserControllerTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void addUser() {
        String url = "http://localhost:6969/user";
        User user = new User("蔡徐坤", "123456", "坤坤", 40, "kun@bug.cn");
        User res = restTemplate.postForObject(url, user, User.class);
        System.out.println(res);
    }
}