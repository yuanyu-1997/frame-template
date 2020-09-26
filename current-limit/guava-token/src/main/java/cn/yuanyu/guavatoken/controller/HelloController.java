package cn.yuanyu.guavatoken.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyu
 */
@Slf4j
@RestController
public class HelloController {

    // http://localhost:5000/guava/hello
    @GetMapping("/hello")
    public String hello() {
        return "hello, world!";
    }
}
