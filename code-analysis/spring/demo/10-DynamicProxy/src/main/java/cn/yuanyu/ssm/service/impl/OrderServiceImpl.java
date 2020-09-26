package cn.yuanyu.ssm.service.impl;

import cn.yuanyu.ssm.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 业务实现类
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public void reduceStock() {
        try {
            log.info("预减库存中...");
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}