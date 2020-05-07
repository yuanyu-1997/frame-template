package com.yuanyu.multids.service.impl;

import com.yuanyu.multids.aop.datasource.DataSourceName;
import com.yuanyu.multids.aop.datasource.TargetDataSource;
import com.yuanyu.multids.domain.order.Order;
import com.yuanyu.multids.repository.order.OrderRepository;
import com.yuanyu.multids.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @TargetDataSource(name = DataSourceName.ORDER)
    public void insertOrder(Order order) {
        orderRepository.save(order);
    }
}
