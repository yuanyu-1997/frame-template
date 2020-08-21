package cn.yuanyu.websocket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/")
public class HelloWorldController {

    // http://localhost:2000/hello
    @GetMapping("/hello")
    public String hello() {
        return "hello world.";
    }

}
