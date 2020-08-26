package cn.yuanyu.qrcode.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Component
public class QrCodeLoginHandshakeInterceptor implements HandshakeInterceptor {

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession httpSession = servletRequest.getServletRequest().getSession(false);
            if (httpSession != null) {
                // 使用 sessionid 区分WebSocketHandler，以便定向发送消息
                String sessionid = (String) httpSession.getAttribute("sessionid");
                String clientip = (String) httpSession.getAttribute("clientip");
                if (sessionid == null) {
                    sessionid = "system-" + httpSession.getId();
                }
                if (clientip == null) {
                    clientip = "null";
                }
                attributes.put("sessionid", sessionid);
                attributes.put("clientip", clientip);
            }
        }

        return true;
    }

    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}  
