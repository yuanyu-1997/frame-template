package cn.yuanyu.reflex.controller;


import cn.yuanyu.reflex.AutoWired;
import cn.yuanyu.reflex.service.UserService;

public class UserController {

    @AutoWired
    public UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
