package cn.yuanyu.websocket.websocket;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@Slf4j
@Component
@ServerEndpoint(value = "/connect/{userId}")
public class WebSocketConnTest {

    /**
     * 建立连接
     *
     * @param userId  用户ID（唯一标识）
     * @param session 会话 javax.websocket.Session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        log.info("和客户端建立连接...");
        session.getAsyncRemote().sendText("你好" + userId);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("客户端发生异常...");
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        log.info("客户端连接关闭...");
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端的消息 => {}", message);
    }

}
