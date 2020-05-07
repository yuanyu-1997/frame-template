package com.yuanyu.multids.service;

import com.yuanyu.multids.domain.order.Order;
import com.yuanyu.multids.domain.user.User;
import com.yuanyu.multids.service.impl.OrderServiceImpl;
import com.yuanyu.multids.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void testInsert(){
        User kun = userService.findByName("蔡徐坤");
        Order order = new Order("12347851246", "男士红内裤", kun.getId());
        orderService.insertOrder(order);
    }

    @Test
    public void test(){
        Order order = new Order("12347851246", "男士红内裤",1L);
        orderService.insertOrder(order);
    }
}