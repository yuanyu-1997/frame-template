package cn.yuanyu.qrcode.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class StringRedisServiceImpl {

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    /**
     * 缓存value操作
     *
     * @param k    key
     * @param v    value
     * @param time 过期时间（秒）
     * @return 是否加入redis
     */
    public boolean cacheValue(String k, String v, long time) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            log.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     */
    public boolean containsValueKey(String k) {
        return containsKey(k);
    }

    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            log.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 获取缓存
     *
     * @param k key
     * @return value
     */
    public String getValue(String k) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get(k);
        } catch (Throwable t) {
            log.error("获取缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

}
