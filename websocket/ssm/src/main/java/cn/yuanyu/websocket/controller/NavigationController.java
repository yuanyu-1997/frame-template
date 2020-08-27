package cn.yuanyu.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NavigationController {

    // http://localhost:3000/websocket
    @GetMapping("/websocket")
    public String websocket(){
        return "websocket";
    }

}
