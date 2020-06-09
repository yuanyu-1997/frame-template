package cn.yuanyu.log.service.impl;


import cn.yuanyu.log.annotation.CountTime;
import cn.yuanyu.log.service.HelloService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author yuanyu
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    @CountTime
    public String world() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        return "world";
    }
}
