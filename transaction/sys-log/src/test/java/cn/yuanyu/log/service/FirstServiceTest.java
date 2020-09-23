package cn.yuanyu.log.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FirstServiceTest {

    @Autowired
    private FirstService firstService;



    @Test
    public void m1() {
        firstService.m1();
    }




    @Test
    public void m3() {
        firstService.m3();
    }

    @Test
    public void m4() {
        firstService.m4();
    }

}