package cn.yuanyu.robredpacket.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 发送红包的信息
 */
@Data
@TableName("red_packet_info")
public class RedPacketInfo {
    /**
     * 自增内码
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 红包id
     */
    private Long redPacketId;
    /**
     * 用户标识
     */
    private Integer uid;

    /**
     * 红包总金额，单位分
     */
    private Integer totalAmount;
    /**
     * 红包总个数
     */
    private Integer totalPacket;

    /**
     * 剩余红包金额，单位分
     */
    private Integer remainingAmount;
    /**
     * 剩余红包个数
     */
    private Integer remainingPacket;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
