package cn.yuanyu.robredpacket.controller;

import cn.yuanyu.robredpacket.request.GetPacketReq;
import cn.yuanyu.robredpacket.request.SaveRedPacketReq;
import cn.yuanyu.robredpacket.domain.RedPacketInfo;
import cn.yuanyu.robredpacket.domain.RedPacketRecord;
import cn.yuanyu.robredpacket.service.RedPacketInfoService;
import cn.yuanyu.robredpacket.service.RedPacketRecordService;
import cn.yuanyu.robredpacket.service.RedisService;
import cn.yuanyu.robredpacket.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping("red_packet_info")
public class RedPacketInfoController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedPacketInfoService redPacketInfoService;

    @Autowired
    private RedPacketRecordService redPacketRecordService;

    private static final String TOTAL_NUM = "_totalNum";

    private static final String TOTAL_AMOUNT = "_totalAmount";

    /**
     * 发红包
     *
     * @param req 用户信息
     */
    // http://localhost:4000/red_packet_info/addPacket/
    @PostMapping("addPacket")
    public JsonData saveRedPacket(@RequestBody SaveRedPacketReq req) {
        RedPacketInfo redPacketInfo = new RedPacketInfo();
        redPacketInfo.setUid(req.getUid());                     // 用户标识
        redPacketInfo.setTotalAmount(req.getTotalAmount());     // 红包总金额
        redPacketInfo.setTotalPacket(req.getTotalNum());        // 红包总个数
        redPacketInfo.setRemainingAmount(req.getTotalAmount()); // 剩余红包金额
        redPacketInfo.setRemainingPacket(req.getTotalNum());    // 剩余红包个数
        // TODO 分布式情况下，最好使用雪花算法生成
        Long redPacketId = System.currentTimeMillis();
        redPacketInfo.setRedPacketId(redPacketId);              // 红包id
        redPacketInfoService.insert(redPacketInfo);
        // 往redis插入2条记录
        // 红包个数
        redisService.set(redPacketId + TOTAL_NUM, req.getTotalNum() + "");
        // 红包金额
        redisService.set(redPacketId + TOTAL_AMOUNT, req.getTotalAmount() + "");
        return JsonData.buildSuccess();
    }

    /**
     * 抢红包
     */
    @PostMapping("getPacket")
    public JsonData getRedPacket(@RequestBody GetPacketReq req) {
        int randomAmount = 0;
        // 红包总个数
        String redPacketName = req.getRedPacketId() + TOTAL_NUM;
        // 红包总金额
        String totalAmountName = req.getRedPacketId() + TOTAL_AMOUNT;
        if (redisService.exists(redPacketName)) {
            int num = Integer.parseInt(redisService.get(redPacketName) != null ? redisService.get(redPacketName) : "0");
            // TODO 有问题
            if (num > 0) {
                // 红包数减一
                redisService.decr(redPacketName);
                // 拿到红包总金额
                if (redisService.exists(totalAmountName)) {
                    int totalAmount = Integer.parseInt(redisService.get(totalAmountName) != null ? redisService.get(totalAmountName) : "0");
                    // 红包最大金额
                    int maxMoney = totalAmount / num * 2;
                    Random random = new Random();
                    // 随机抢到的红包
                    randomAmount = num == 1 ? totalAmount : random.nextInt(maxMoney);
                    // redis红包金额减指定值
                    redisService.decr(totalAmountName, randomAmount);
                }
                updateRedPacketInDB(req, randomAmount);
                log.info("抢红包成功 => {}", req.getNickName());
                return JsonData.buildSuccess("抢到红包:" + randomAmount);
            } else {
                log.info("抢红包失败 => {}", req.getNickName());
                return JsonData.buildError("红包被抢完啦");
            }
        } else {
            log.info("红包不存在 => {}", req.getNickName());
            return JsonData.buildError("红包不存在，请重试");
        }
    }

    /**
     * 插入用户抢到红包记录
     *
     * @param req    GetPacketReq
     * @param amount 金额
     */
    private void updateRedPacketInDB(GetPacketReq req, int amount) {
        RedPacketRecord redPacketRecord = new RedPacketRecord();
        redPacketRecord.setUid(req.getUid());                    // 用户id
        redPacketRecord.setRedPacketId(req.getRedPacketId());    // 红包id
        redPacketRecord.setAmount(amount);                       // 金额
        redPacketRecord.setNickName(req.getNickName());
        redPacketRecordService.insert(redPacketRecord);
    }
}
