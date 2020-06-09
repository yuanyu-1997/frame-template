package com.nobug.controller;


import com.nobug.entity.Brand;
import com.nobug.entity.User;
import com.nobug.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BrandControllerTest {

    @Test
    public void testInsertBrand() {
        Brand brand = new Brand();
        brand.setName("华为");
        brand.setFirstChar('H');
        String url = "http://localhost:6969/brand";
        ResponseEntity<Result> res = new RestTemplate().postForEntity(url, brand, Result.class);
        System.out.println(res);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("蔡徐坤");
        user.setPassword("123456789");
        user.setName("坤坤");
        user.setSex('1');
        String url = "http://localhost:6969/user";
        ResponseEntity<Result> res = new RestTemplate().postForEntity(url, user, Result.class);
        System.out.println(res);
    }

    @Test
    public void test_() {
        for (int i = 0; i < 500000; i++) {
            testInsertBrand();
        }
    }
}