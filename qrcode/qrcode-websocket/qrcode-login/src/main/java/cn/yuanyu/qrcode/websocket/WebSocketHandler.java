package cn.yuanyu.qrcode.websocket;


import cn.yuanyu.qrcode.util.R;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Websocket处理器
 */
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    // 已建立连接的用户 <>
    private static final Map<String, WebSocketSession> sessions = new HashMap<>();

    /**
     * 当新连接建立的时候被调用（连接成功时候，会触发页面上onOpen方法）
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String sessionid = (String) session.getAttributes().get("sessionid");
        String clientip = (String) session.getAttributes().get("clientip");
        log.info("clientip: " + clientip + " webSocket连接成功! sessionid:" + sessionid);

        if (sessionid != null) {
            sessions.put(sessionid, session);
        }
    }

    /**
     * 处理前端发送的文本信息（js调用 websocket.send 时候会调用该方法）
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String sessionid = (String) session.getAttributes().get("sessionid");
        String clientip = (String) session.getAttributes().get("clientip");
        // 获取提交过来的消息详情
        log.info("收到clientip: " + clientip + "的消息:" + message.toString() + " sessionid:" + sessionid);
        // 回复一条信息，
        log.info("消息内容：" + message.getPayload());
        Map<String, String> acceptMap = JSON.parseObject(message.getPayload(), new TypeReference<HashMap<String, String>>() {});
        int msgtype = Integer.parseInt(acceptMap.get("msgtype"));
        // 二维码过期
        if (msgtype == 1) {
            log.info("二维码过期，移除session:" + sessionid);
            sessions.remove(sessionid);
        }
        R res = R.ok().put("msgtype", msgtype);
        session.sendMessage(new TextMessage(JSON.toJSONString(res)));
    }

    /**
     * 当连接关闭时被调用
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionid = (String) session.getAttributes().get("sessionid");
        String clientip = (String) session.getAttributes().get("clientip");
        log.info("clientip:" + clientip + " sessionid:" + sessionid + " webSocket 连接关闭. Status: " + status);
        sessions.remove(sessionid);
    }

    /**
     * 传输错误时调用
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String sessionid = (String) session.getAttributes().get("sessionid");
        String clientip = (String) session.getAttributes().get("clientip");
        if (session.isOpen()) {
            session.close();
        }
        log.debug("clientip:" + clientip + "sessionid:" + sessionid + " webSocket 连接关闭......");
        sessions.remove(sessionid);
    }

    /**
     * 给某个用户发送消息
     */
    public synchronized boolean sendMessageToUser(String sessionid, TextMessage message) {
        try {
            WebSocketSession session = sessions.get(sessionid);
            if (session != null && sessionid.equals(session.getAttributes().get("sessionid")) && session.isOpen()) {
                session.sendMessage(message);
                return true;
            } else {
                log.info("sessionid:" + sessionid + "已失效");
            }
        } catch (IOException e) {
            log.error("websocket通知客户端异常：" + e.toString());
            e.printStackTrace();
        }
        return false;
    }

}

