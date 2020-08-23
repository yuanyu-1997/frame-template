package cn.yuanyu.websocket.websocket;

import cn.yuanyu.websocket.config.ObjectEncoder;
import cn.yuanyu.websocket.controller.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Component
@ServerEndpoint(value = "/ws/chart/{userId}", encoders = {ObjectEncoder.class})
public class WebSocketServer {

    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
    public static final CopyOnWriteArraySet<Session> SESSION_SET = new CopyOnWriteArraySet<Session>();
    public static final Map<String, String> USER_SESSION_RELATION = new HashMap<>();

    /**
     * 连接建立成功调用的方法
     **/
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        log.info("有连接加入 => {}", userId);
        SESSION_SET.add(session);
        // 用户ID => session id
        USER_SESSION_RELATION.put(userId, session.getId());
        int cnt = ONLINE_COUNT.incrementAndGet(); // 在线数加1
        log.info("有连接加入，当前连接数为：{}", cnt);
        sendMessage(session, "连接成功");
    }

    /**
     * 收到客户端的消息
     *
     * @param message 客户端发送过来的消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息：{}", message);
        broadCastInfo(message);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        SESSION_SET.remove(session);
        //TODO 清除关系
        int cnt = ONLINE_COUNT.decrementAndGet();
        log.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 出现错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID：{}", error.getMessage(), session.getId());
        //
        //TODO 清除关系
        ONLINE_COUNT.decrementAndGet();
    }


    /**
     * 发送消息（每次浏览器刷新，session会发生变化）
     *
     * @param session javax.websocket.Session
     * @param message 消息
     */
    public static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendObject(new Message(session.getId(), message));
            // session.getBasicRemote().sendText();
        } catch (IOException | EncodeException e) {
            log.error("发送消息出错：{}", e.getMessage());
        }
    }

    /**
     * 群发消息
     *
     * @param message 消息
     */
    public static void broadCastInfo(String message) {
        for (Session session : SESSION_SET) {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        }
    }

    /**
     * 指定用户发送消息
     *
     * @param userId  发送消息的用户id
     * @param message 消息
     */
    public static void sendMessage(String message, String userId) {
        String sessionId = USER_SESSION_RELATION.get(userId);
        Session session = null;
        for (Session s : SESSION_SET) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            sendMessage(session, message);
        } else {
            log.warn("没有找到你指定ID的会话：{}", sessionId);
        }
    }

}