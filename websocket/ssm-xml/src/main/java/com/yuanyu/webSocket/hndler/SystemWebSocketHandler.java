/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuanyu.webSocket.hndler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class SystemWebSocketHandler implements WebSocketHandler {

    // 在线用户列表
    private static final Map<String, WebSocketSession> users = new HashMap<>();

    // 用户标识
    private static final String USER_KEY = "username";

    /**
     * 连接就绪时
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        String username = getClientId(session);
        System.out.println("连接就绪 username => " + username);
        if (username != null) {
            users.put(username, session);
            session.sendMessage(new TextMessage("成功建立socket连接（来自服务端的消息）"));
        }
    }

    /**
     * 处理客户端信息
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> wsm) {
        String username = getClientId(session);
        System.out.println("来消息了 => " + username + ":" + wsm.getPayload());
    }

    /**
     * 处理传输时异常
     */
    @Override
    public void handleTransportError(WebSocketSession wss, Throwable thrwbl) {
        System.out.println("处理传输时异常...");
    }

    /**
     * 关闭连接时
     */
    @Override
    public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) {
        System.out.println("关闭连接时...");
    }

    /**
     * 是否支持分包
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     */
    private String getClientId(WebSocketSession session) {
        try {
            return (String) session.getAttributes().get(USER_KEY);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 广播
     *
     * @param msg 广播内容
     */
    public void broadcast(String msg) {
        for (Map.Entry<String, WebSocketSession> entry : users.entrySet()) {
            String name = entry.getKey();
            WebSocketSession session = entry.getValue();
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(msg));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
