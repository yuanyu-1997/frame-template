package cn.yuanyu.robredpacket.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户抢红包的记录
 */
@Data
@TableName("red_packet_record")
public class RedPacketRecord {
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
     * 用户id
     */
    private Integer uid;
    /**
     * 抢到红包的用户
     */
    private String nickName;
    /**
     * 抢到红包的金额
     */
    private Integer amount;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
