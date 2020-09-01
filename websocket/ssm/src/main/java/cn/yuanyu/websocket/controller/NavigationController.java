package cn.yuanyu.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NavigationController {


    // http://localhost:15736/emm-cgi/page

    // http://localhost:58443/emm-cgi/websocket
    // http://localhost:15736/emm-cgi/websocket
    @GetMapping("/page")
    public String websocket(){
        return "websocket";
    }

}
