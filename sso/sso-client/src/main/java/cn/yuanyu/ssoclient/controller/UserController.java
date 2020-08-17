package cn.yuanyu.ssoclient.controller;

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
    // http://127.0.0.1:5000/user-center/user/userIndex
    @RequestMapping("/userIndex")
    public String userIndex(){
        return "userIndex";
    }

    /**
     * 一个测试的受保护的页面
     */
    // http://127.0.0.1:5000/user-center/user/protected
    @RequestMapping("/protected")
    public String protectedPage(){
        return "protected";
    }
}
