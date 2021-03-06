package cn.yuanyu.websocket.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketStompConfig {
    /**
     * 用于扫描带有 @ServerEndpoint 的注解成为websocket，如果你使用外置的tomcat就不需要这个
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}