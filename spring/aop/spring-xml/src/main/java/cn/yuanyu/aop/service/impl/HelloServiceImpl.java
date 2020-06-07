package cn.yuanyu.aop.service.impl;


import cn.yuanyu.aop.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author yuanyu
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String doSomething() {
        return "ok";
    }
}
