package cn.yuanyu.limitflowguava.controller;

import cn.yuanyu.limitflowguava.common.annotation.LimitFlow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @LimitFlow
    @GetMapping("/limitflow")
    public String hello(){
        return "hello";
    }
}
