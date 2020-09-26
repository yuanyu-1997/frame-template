package cn.yuanyu.ssm.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Boss {
    @Autowired
    private Car car;
    public void show() {
        System.out.println("car => " + this.car);
    }
}