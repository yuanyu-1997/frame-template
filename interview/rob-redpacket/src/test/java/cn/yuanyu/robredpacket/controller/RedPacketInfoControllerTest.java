package cn.yuanyu.robredpacket.controller;

import cn.yuanyu.robredpacket.request.GetPacketReq;
import cn.yuanyu.robredpacket.request.SaveRedPacketReq;
import cn.yuanyu.robredpacket.utils.JsonData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedPacketInfoControllerTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 发红包
    @Test
    public void test_send() throws JsonProcessingException {
        SaveRedPacketReq req = new SaveRedPacketReq();
        req.setUid((int) (Math.random() * 1000)); // 用户ID
        req.setTotalAmount(100);                  // 金额
        req.setTotalNum(10);                      // 个数
        String url = "http://localhost:4000/red_packet_info/addPacket/";
        log.info("param => {}", objectMapper.writeValueAsString(req));
        JsonData res = new RestTemplate().postForObject(url, req, JsonData.class);
        log.info("res => {}", res);
    }


    // 抢红包
    @Test
    public void test_get() throws JsonProcessingException {
        GetPacketReq req = new GetPacketReq();
        req.setUid((int) (Math.random() * 1000)); // 用户ID
        req.setRedPacketId(1599375490663L); // 红包id（）
        req.setNickName(UUID.randomUUID().toString().replace("-", "").substring(10));
        String url = "http://localhost:4000/red_packet_info/getPacket/";
        // {"uid":788,"redPacketId":1599375490663,"nickName":"724c0f9c98bbbb72219fa0"}
        log.info("req => {}", objectMapper.writeValueAsString(req));
        JsonData res = new RestTemplate().postForObject(url, req, JsonData.class);
        log.info("res => {}", objectMapper.writeValueAsString(res));
    }

    // ab -n 1000 -c 1000 -p req.txt -T application/json http://localhost:4000/red_packet_info/getPacket/


}