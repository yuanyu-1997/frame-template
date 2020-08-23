package com.yuanyu.webSocket.controller;

import com.yuanyu.webSocket.hndler.SystemWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
public class SocketController {

    @Autowired
    private SystemWebSocketHandler systemWebSocketHandler;

    // http://localhost:8084/login/张三.do
    @ResponseBody
    @PostMapping("/login/{username}")
    public String login(@PathVariable("username") String username, HttpSession session) {
        System.out.println("登录接口 => " + username);
        System.out.println("Controller中 => " + session + " : " + session.getId());
        //
        session.setAttribute("username", username);
        return "ok";
    }
}