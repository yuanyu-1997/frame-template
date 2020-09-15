package cn.yuanyu.frequencylimit.service.impl;

import cn.yuanyu.frequencylimit.service.CurrentLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
// https://www.cnblogs.com/lhc-hhh/p/13176505.html
// https://blog.csdn.net/baidu_38610980/article/details/106236311
// https://blog.csdn.net/java_zone/article/details/52861930

@Slf4j
@Service
public class CurrentLimitServiceImpl implements CurrentLimitService {

    @Autowired
    private RedisService redisService;


    /**
     * 判断是否超出限制
     *
     * @param ip      客户端IP地址
     * @param timeout 时间段（毫秒）
     * @param count   时间段内允许访问的次数
     */
    public boolean limitSendCount(String ip, long timeout, int count) {
        String redisKey = "limit" + ":" + ip;
        String value = redisService.get(redisKey);
        if (value == null) {
            redisService.set(redisKey, "1", timeout, TimeUnit.MILLISECONDS);
            return true;
        } else {
            int currentCount = Integer.parseInt(value);
            // 限制访问
            if (currentCount >= count) {
                return false;
            }
            redisService.incr(redisKey);
        }
        return true;
    }

}
