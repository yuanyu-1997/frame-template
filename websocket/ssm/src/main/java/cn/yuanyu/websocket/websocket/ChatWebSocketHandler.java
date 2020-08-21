package cn.yuanyu.websocket.websocket;


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
/**
 *
 */
@Service
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 在线用户列表
    private static final Map<String, WebSocketSession> users = new HashMap<>();
    // 用户标识
    private static final String CLIENT_ID = "username";

    /**
     * 当新连接建立的时候，被调用（连接成功时候，会触发页面上onOpen方法 ）
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("成功建立连接");
        String username = getClientId(session);
        System.out.println("username => " + username);
        if (username != null) {
            users.put(username, session);
            session.sendMessage(new TextMessage("成功建立socket连接"));
            System.out.println(username);
            System.out.println(session);
        }
    }

    /**
     * 处理前端发送的文本信息（js调用 websocket.send 时候，会调用该方法 ）
     * @param message 文本信息
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println(message.getPayload());
        TextMessage textMessage = new TextMessage("server:" + message);
        try {
            session.sendMessage(textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 当连接关闭时被调用
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("连接已关闭：" + status);
        users.remove(getClientId(session));
    }

    /**
     * 传输错误时调用
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.out.println("连接出错");
        users.remove(getClientId(session));
    }

//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }

    /**
     * 发送信息给指定用户
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (users.get(clientId) == null) {
            return false;
        }
        WebSocketSession session = users.get(clientId);
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
        Set<String> clientIds = users.keySet();
        WebSocketSession session;
        for (String clientId : clientIds) {
            try {
                session = users.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(message);
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
            return (String) session.getAttributes().get(CLIENT_ID);
        } catch (Exception e) {
            return null;
        }
    }
}