package cn.yuanyu.websocket.controller.dto;


import lombok.Data;

@Data
public class Message {
    /**
     * 当前用户的 sessionId
     */
    private String sessionId;
    /**
     * 消息内容
     */
    private String msg;

    public Message() {
    }

    public Message(String sessionId, String msg) {
        this.sessionId = sessionId;
        this.msg = msg;
    }
}
