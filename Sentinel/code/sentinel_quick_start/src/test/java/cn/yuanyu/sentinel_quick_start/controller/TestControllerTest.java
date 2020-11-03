package cn.yuanyu.sentinel_quick_start.controller;


import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class TestControllerTest {
    private static final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void hello() throws InterruptedException {
        String url = "http://localhost:6969/hello";
        while (true) {
            String res = restTemplate.getForObject(url, String.class);
            System.out.println("res => " + res);
            Thread.sleep(new Random().nextInt(2000));
        }
    }
}