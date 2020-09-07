package cn.yuanyu.robredpacket.service.impl;

import cn.yuanyu.robredpacket.domain.RedPacketRecord;
import cn.yuanyu.robredpacket.mapper.RedPacketRecordMapper;
import cn.yuanyu.robredpacket.service.RedPacketRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RedPacketRecordServiceImpl implements RedPacketRecordService {
    @Resource
    private RedPacketRecordMapper redPacketRecordMapper;
    @Override
    public void insert(RedPacketRecord redPacketRecord) {
        redPacketRecordMapper.insert(redPacketRecord);
    }
}
