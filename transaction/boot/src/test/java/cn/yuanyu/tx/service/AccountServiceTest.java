package cn.yuanyu.tx.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Before
    public void before() {
        System.out.println();
        System.out.println("before...");
        System.out.println(accountService.findAccountByName("张三"));
        System.out.println(accountService.findAccountByName("李四"));
    }

    @After
    public void after() {
        System.out.println();
        System.out.println("after...");
        System.out.println(accountService.findAccountByName("张三"));
        System.out.println(accountService.findAccountByName("李四"));
    }

    //@Test
    //public void findAccountByName() {
    //}

    @Test
    public void transfer() {
        accountService.transfer("张三", "李四", 100f);
    }
}