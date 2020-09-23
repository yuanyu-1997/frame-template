package cn.yuanyu.log.service.impl;

import cn.yuanyu.log.service.SecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("secondService")
public class SecondServiceImpl implements SecondService {

    @Autowired
    private FirstServiceImpl firstService;

    @Override
    public void m3() {
        firstService.m2();
    }
}
