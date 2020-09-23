package cn.yuanyu.log.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SecondServiceTest {

    @Autowired
    private SecondService secondService;


    @Test
    public void m3() {
        secondService.m3();
    }
}