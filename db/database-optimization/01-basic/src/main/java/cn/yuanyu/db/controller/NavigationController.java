package cn.yuanyu.db.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    // http://localhost:5000/logPage
    @GetMapping("/logPage")
    public String logPage(){
        return "log-list";
    }
}
