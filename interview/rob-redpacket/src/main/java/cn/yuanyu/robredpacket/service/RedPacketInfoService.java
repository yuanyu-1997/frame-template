package cn.yuanyu.robredpacket.service;

import cn.yuanyu.robredpacket.domain.RedPacketInfo;

public interface RedPacketInfoService {
    /**
     * 添加红包
     */
    void insert(RedPacketInfo redPacketInfo);
}
