package cn.yuanyu.log.controller;

import cn.yuanyu.log.annotation.CountTime;
import cn.yuanyu.log.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @CountTime //统计方法耗时
    @GetMapping("/hello")
    public String hello() {
        return "world";
    }

    @GetMapping("/world")
    public String world() {
        return helloService.world();
    }
}
