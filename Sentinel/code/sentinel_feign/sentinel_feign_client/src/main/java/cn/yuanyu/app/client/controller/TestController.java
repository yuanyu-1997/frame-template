package cn.yuanyu.app.client.controller;

import cn.yuanyu.app.client.agent.FeignAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyu
 */
// GET:http://sentinel-feign-provider/hello
@RestController
public class TestController {

    @Autowired
    private FeignAgent feignAgent;

    // http://localhost:9012/hello
    @GetMapping("hello")
    public String hello(){
        return feignAgent.hello();
    }
}
