package cn.yuanyu.websocket.interceptor;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

// https://blog.csdn.net/wangfuxu14/article/details/81774307
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {
    // WebSocket connection to 'ws://localhost:3000/websocket/chat' failed: Error during WebSocket handshake: Unexpected response code: 500

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
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                System.out.println("Interceptor => " + session + " : " + session.getId());
                attributes.put("username", session.getAttribute("username"));
            }
        }
        return true;
    }

    /**
     * 在握手之后执行该方法，无论是否握手成功都指明了响应状态码和相应头
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
}


//
//@Component
//public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map<String, Object> map) {
//        if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
//            HttpSession session = serverHttpRequest.getServletRequest().getSession();
//            System.out.println(session + " => " + session.getId());
//            if (session != null) {
//                map.put("username", session.getAttribute("username"));
//            }
//        }
//
//        return true;
//    }
//    @Override
//    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) { }
//}



