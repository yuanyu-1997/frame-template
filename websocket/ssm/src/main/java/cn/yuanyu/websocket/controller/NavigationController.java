package cn.yuanyu.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {

    // http://localhost:58443/emm-cgi/login
    // http://localhost:15736/emm-cgi/login
    @GetMapping("/login")
    public String websocket(){
        return "websocket";
    }

}
