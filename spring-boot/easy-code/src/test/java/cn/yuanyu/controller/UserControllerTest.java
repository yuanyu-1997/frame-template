package cn.yuanyu.controller;

import cn.yuanyu.entity.User;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class UserControllerTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void selectOne() {
        String url = "http://localhost:6969/user/1";
        User res = restTemplate.getForObject(url, User.class);
        System.out.println(res);
    }

    @Test
    public void queryAll() {
        String url = "http://localhost:6969/user/";
        List users = restTemplate.getForObject(url, List.class);
        users.forEach(System.out::println);
    }
}