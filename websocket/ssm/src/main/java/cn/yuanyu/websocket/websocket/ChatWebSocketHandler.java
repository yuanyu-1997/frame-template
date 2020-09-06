package cn.yuanyu.websocket.websocket;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
// https://www.jb51.net/article/137433.htm
// https://blog.csdn.net/HOMERUNIT/article/details/80861096
// https://www.cnblogs.com/nosqlcoco/p/5860730.html
// var websocket = new WebSocket("ws://127.0.0.1:2000/websocket");

// websocket.send('客户端发送消息测试');
@Slf4j
@Service
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 在线用户列表
    private static final Map<String, WebSocketSession> ONLINE_USERS = new HashMap<>();
    // 用户标识
    private static final String USER_KEY = "username";

    /**
     * 当新连接建立的时候，被调用（连接成功时候，会触发页面上onOpen方法 ）
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("服务端成功建立连接...");
        String username = getClientId(session);
        log.info("username => {}", username);
        if (username != null) {
            ONLINE_USERS.put(username, session);
            session.sendMessage(new TextMessage("server: 成功建立socket连接"));
        }
    }

    /**
     * 处理前端发送的文本信息（js调用 websocket.send 时候，会调用该方法 ）
     *
     * @param message 文本信息
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String username = getClientId(session);
        TextMessage textMessage = new TextMessage(username + ": " + message.getPayload());
        sendMessageToAllUsers(textMessage);
    }

    /**
     * 当连接关闭时被调用
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("连接已关闭 => {}", status);
        ONLINE_USERS.remove(getClientId(session));
    }

    /**
     * 传输错误时调用
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        log.info("连接出错...");
        ONLINE_USERS.remove(getClientId(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 发送信息给指定用户
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (ONLINE_USERS.get(clientId) == null) {
            return false;
        }
        WebSocketSession session = ONLINE_USERS.get(clientId);
        if (!session.isOpen()) {
            return false;
        }
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 广播信息
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> clientIds = ONLINE_USERS.keySet();
        WebSocketSession session;
        for (String clientId : clientIds) {
            try {
                session = ONLINE_USERS.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message.getPayload()));
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }
        return allSendSuccess;
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
}