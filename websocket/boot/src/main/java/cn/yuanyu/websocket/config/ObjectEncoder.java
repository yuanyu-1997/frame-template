package cn.yuanyu.websocket.config;


import cn.yuanyu.websocket.controller.dto.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

@Slf4j
public class ObjectEncoder implements Encoder.Text<Message> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String encode(Message message) {
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(message);
        } catch (Exception e) {
            log.error("序列化异常 => {}", e.getMessage());
            throw new RuntimeException("Message序列化异常");
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
