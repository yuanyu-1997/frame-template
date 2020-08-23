package cn.yuanyu.qrcode.config;

import cn.yuanyu.qrcode.interceptor.QrCodeLoginHandshakeInterceptor;
import cn.yuanyu.qrcode.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * websocket相关配置文件
 */
@Configuration
@EnableWebSocket
public class QrCodeLoginWebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Autowired
    private QrCodeLoginHandshakeInterceptor interceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 1.注册WebSocket
        String websocket_url = "/websocket/socketServer.do";            // 设置websocket的地址
        registry.addHandler(webSocketHandler, websocket_url).           // 注册Handler
                addInterceptors(interceptor)                            // 注册Interceptor
                .setAllowedOrigins("*");                                // 403
        //WebSocket connection to 'ws://127.0.0.1:30000/heihei/websocket/socketServer.do' failed: Error during WebSocket handshake: Unexpected response code: 403

        // 2.注册SockJS，提供SockJS支持（主要是兼容ie8）
        String sockjs_url = "/sockjs/socketServer.do";                       // 设置sockjs的地址
        registry.addHandler(webSocketHandler, sockjs_url).                   // 注册Handler
                addInterceptors(interceptor)                                 // 注册Interceptor
                .withSockJS();                                               // 支持sockjs协议
    }

}  

