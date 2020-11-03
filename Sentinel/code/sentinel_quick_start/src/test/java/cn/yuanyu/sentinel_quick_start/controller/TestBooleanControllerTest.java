package cn.yuanyu.sentinel_quick_start.controller;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Random;


public class TestBooleanControllerTest {
    private static final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void hello() throws InterruptedException {
        String url = "http://localhost:6969/boolean";
        while (true) {
            String res = restTemplate.getForObject(url, String.class);
            System.out.println("res => " + res);
            Thread.sleep(new Random().nextInt(2000));
        }
    }


}