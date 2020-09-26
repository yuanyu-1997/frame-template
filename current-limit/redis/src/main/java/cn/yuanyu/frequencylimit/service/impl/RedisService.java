package cn.yuanyu.frequencylimit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;


    /**
     * 写入缓存设置时效时间
     */
    public boolean set(String key, String value, Long expireTime, TimeUnit timeUnit) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            stringRedisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存失败: {}", e.getMessage());
        }
        return result;
    }

    /**
     * 读取缓存
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 原子递增
     */
    public boolean incr(String key) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.increment(key);
            result = true;
        } catch (Exception e) {
            log.error("原子递增失败: {}", e.getMessage());
        }
        return result;
    }


}
