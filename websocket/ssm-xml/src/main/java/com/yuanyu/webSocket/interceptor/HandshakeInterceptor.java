package com.yuanyu.webSocket.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

//https://gitee.com/dayu/SpringWebSocketPush
@Component
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    /**
     * 在握手之前执行该方法，继续握手返回true，中断握手返回false
     *
     * @param request    ServerHttpRequest
     * @param response   ServerHttpResponse
     * @param wsHandler  WebSocketHandler
     * @param attributes 设置WebSocketSession的属性
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        System.out.println("beforeHandshake....");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                System.out.println("Interceptor中 => " + session + " : " + session.getId());
                attributes.put("username", session.getAttribute("username"));
            }
        }
        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {

    }

}
