package cn.yuanyu.i18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanyu
 */
@Controller
@RequestMapping("/")
public class HelloController {


    /**
     * http://localhost:8080/jump?dest=i18n
     * http://localhost:8080/jump?dest=noi18n
     */
    @GetMapping("/jump")
    public String jump(String dest) {
        return dest;
    }
}
