package cn.yuanyu.robredpacket.request;


import lombok.Data;

@Data
public class GetPacketReq {
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 红包id
     */
    private Long redPacketId;
    /**
     * 用户名
     */
    private String nickName;
}
