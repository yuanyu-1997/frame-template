package cn.yuanyu.cache.controller;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;


public class UserControllerTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void delete() {
        Integer a = 60 * 60 * 24 ;
        System.out.println(a);
        System.out.println(TimeUnit.SECONDS.toMillis(24));
    }
}