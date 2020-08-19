package cn.yuanyu.packagestatic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/idx")
    private String  index(){

        return "index";
    }
}
