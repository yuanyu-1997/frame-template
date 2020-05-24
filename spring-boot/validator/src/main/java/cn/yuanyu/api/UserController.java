package cn.yuanyu.api;

import cn.yuanyu.service.UserService;
import cn.yuanyu.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户注册
     * http://localhost:6969/user
     */
    @PostMapping
    public void register(@Valid @RequestBody UserModel userModel, BindingResult result) {
        //判断校验是否存在错误
        if (result.hasErrors()) {
            System.out.println(result);
            return;
        }
        //do something
        System.out.println(userModel);
    }

}