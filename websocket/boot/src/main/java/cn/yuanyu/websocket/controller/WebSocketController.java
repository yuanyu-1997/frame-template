package cn.yuanyu.websocket.controller;

import cn.yuanyu.websocket.websocket.WebSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ws")
public class WebSocketController {

    // http://localhost:3000/api/ws/sendAll?message=全体起立

    /**
     * 群发消息内容
     *
     * @param message 消息内容
     */
    @GetMapping(value = "/sendAll")
    public String sendAllMessage(@RequestParam String message) {
        WebSocketServer.broadCastInfo(message);
        return "ok";
    }

    // http://localhost:3000/api/ws/sendOne?message=哈哈&userId=111
    /**
     * 指定用户ID发消息
     *
     * @param message 消息内容
     * @param userId  发送消息的用户id
     */
    @GetMapping(value = "/sendOne")
    public String sendOneMessage(@RequestParam String message, @RequestParam String userId) {
        WebSocketServer.sendMessage(message, userId);
        return "ok";
    }
}