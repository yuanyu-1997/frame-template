package cn.yuanyu.robredpacket.request;

import lombok.Data;

@Data
public class SaveRedPacketReq {
    /**
     * 用户标识
     */
    private Integer uid;
    /**
     * 红包个数
     */
    private Integer totalNum;
    /**
     * 红包金额
     */
    private Integer totalAmount;
}
