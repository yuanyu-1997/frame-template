package cn.yuanyu.websocket.controller;

import cn.yuanyu.websocket.websocket.ChatWebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

/**
 *
 */
@Controller
public class SocketController {

    @Autowired
    private ChatWebSocketHandler handler;

    // http://localhost:2000/login/张三
    @RequestMapping("/login/{username}")
    @ResponseBody
    public String login(@PathVariable("username") String username, HttpSession session) {
        System.out.println("登录接口 => " + username);
        session.setAttribute("username", username);
        return "成功";
    }

    // http://localhost:2000/message?msg=你好
    @RequestMapping("/message")
    @ResponseBody
    public String sendMessage(@RequestParam String msg) {
        boolean flag = handler.sendMessageToAllUsers(new TextMessage(msg));
        if (flag) {
            return "ok";
        } else {
            return "err";
        }
    }
}