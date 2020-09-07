package cn.yuanyu.robredpacket.service.impl;

import cn.yuanyu.robredpacket.domain.RedPacketInfo;
import cn.yuanyu.robredpacket.mapper.RedPacketInfoMapper;
import cn.yuanyu.robredpacket.service.RedPacketInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RedPacketInfoServiceImpl implements RedPacketInfoService {
    @Resource
    private RedPacketInfoMapper redPacketInfoMapper;
    /**
     * 添加红包
     */
    @Override
    public void insert(RedPacketInfo redPacketInfo) {
        redPacketInfoMapper.insert(redPacketInfo);
    }
}
