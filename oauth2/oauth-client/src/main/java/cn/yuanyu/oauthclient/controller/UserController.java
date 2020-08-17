package cn.yuanyu.oauthclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户相关controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 用户首页
     */
    // http://localhost:2500/xxx-client/user/userIndex
    @RequestMapping("/userIndex")
    public String userIndex(){
        return "userIndex";
    }

    /**
     * 一个测试的受保护的页面
     */
    @RequestMapping("/protected")
    public String protectedPage(){
        return "protected";
    }
}
