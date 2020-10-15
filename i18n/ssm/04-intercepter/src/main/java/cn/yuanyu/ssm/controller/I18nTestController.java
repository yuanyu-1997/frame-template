package cn.yuanyu.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author yuanyu
 */
@Controller
public class I18nTestController {
    /**
     * 跳转到登陆页面
     */
    @GetMapping("/toLoginPage")
    public String toLoginPage() {
        return "login";
    }

}
