package com.yuanyu.multids.service;


import com.yuanyu.multids.domain.order.Order;

public interface OrderService {
    /**
     * 插入一条订单记录
     */
    void insertOrder(Order order);
}
