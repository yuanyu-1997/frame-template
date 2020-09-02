package cn.yuanyu.websocket.controller;

import cn.yuanyu.websocket.websocket.ChatWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

/**
 *
 */
@CrossOrigin
@Controller
public class SocketController {

    @Autowired
    private ChatWebSocketHandler handler;

    // http://localhost:58443/login/emm-cgi/张三
    @ResponseBody
    @PostMapping("/login/{username}")
    public String login(@PathVariable("username") String username, HttpSession session) {
        System.out.println("Controller => " + session + " : " + session.getId());
        System.out.println("登录接口 => " + username);
        session.setAttribute("username", username);
        return "ok";
    }

    /**
     * 推送消息
     */
    // http://localhost:58443/emm-cgi/message?msg=你好
    @RequestMapping("/message")
    @ResponseBody
    public String sendMessage(@RequestParam String msg) {
        boolean flag = handler.sendMessageToAllUsers(new TextMessage("admin: " + msg));
        if (flag) {
            return "ok";
        } else {
            return "err";
        }
    }
}