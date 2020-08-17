package cn.yuanyu.oauthclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tets")
public class TestController {

    // http://localhost:2500/xxx-client/tets/p?name=666&name=999
    @GetMapping("/p")
    public String name(String name){
        return name;
    }
}
