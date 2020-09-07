package cn.yuanyu.redisboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// https://yuanyu.blog.csdn.net/article/details/90606484
// https://yuanyu.blog.csdn.net/article/details/108029342

@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    // 操作字符串（String）

    /**
     * 写入缓存
     */
    // set key value
    public boolean set(String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存失败: {}", e.getMessage());
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间（秒）
     */
    public boolean set(String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存失败: {}", e.getMessage());
        }
        return result;
    }

    /**
     * 读取缓存
     */
    // get key
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     *
     * @param key    key
     * @param offset 位 8Bit=1Byte
     */
    // setbit bit 10086 1 => 10086位被设置为1
    // getbit bit 10086   => 获取10086位的值
    public boolean setBit(String key, long offset, boolean isShow) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.setBit(key, offset, isShow);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存失败: {}", e.getMessage());
        }
        return result;
    }


    /**
     * 读取缓存
     */
    public Boolean getBit(String key, long offset) {
        Boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            result = operations.getBit(key, offset);
        } catch (Exception e) {
            log.error("读取缓存失败: {}", e.getMessage());
        }
        return result;
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

    /**
     * 原子递增
     */
    public boolean incr(String key, int value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.increment(key, value);
            result = true;
        } catch (Exception e) {
            log.error("原子递增失败: {}", e.getMessage());
        }
        return result;
    }

    /**
     * 原子递减
     */
    public boolean decr(String key) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.decrement(key);
            result = true;
        } catch (Exception e) {
            log.error("原子递减失败: {}", e.getMessage());
        }
        return result;
    }

    /**
     * 原子递减
     */
    public boolean decr(String key, int value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.decrement(key, value);
            result = true;
        } catch (Exception e) {
            log.error("原子递减失败: {}", e.getMessage());
        }
        return result;
    }


    /**
     * 批量删除对应的value
     */
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 删除对应的value
     */
    // del key
    public void remove(final String key) {
        if (exists(key)) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     */
    public Boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }


    // 操作集合（Set）

    /**
     * 哈希 添加
     */
    //  hset key field value
    public void hmSet(String key, String hashKey, String value) {
        HashOperations<String, String, String> hash = stringRedisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希获取数据
     */
    // hget key field
    public Object hmGet(String key, String hashKey) {
        HashOperations<String, String, String> hash = stringRedisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    // 操作列表（List）
    /**
     * 列表添加
     */
    // lpush key value
    public void lPush(String k, String v) {
        ListOperations<String, String> list = stringRedisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 列表获取
     */
    // lrange key start end
    public List<String> lRange(String k, long l, long l1) {
        ListOperations<String, String> list = stringRedisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    // 操作集合（Set）

    /**
     * 集合添加
     */
    // sadd key value
    public void add(String key, String value) {
        SetOperations<String, String> set = stringRedisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 集合获取
     */
    // smembers key
    public Set<String> setMembers(String key) {
        SetOperations<String, String> set = stringRedisTemplate.opsForSet();
        return set.members(key);
    }

    // 操作有序集合（zSet）

    /**
     * 有序集合添加
     */
    // zadd key score value
    public void zAdd(String key, String value, double scoure) {
        ZSetOperations<String, String> zset = stringRedisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * 有序集合获取
     */
    // zrange key start end
    public Set<String> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, String> zset = stringRedisTemplate.opsForZSet();
        stringRedisTemplate.opsForValue();
        return zset.rangeByScore(key, scoure, scoure1);
    }


    /**
     * 有序集合获取排名
     *
     * @param key   集合名称
     * @param value 值
     */

    public Long zRank(String key, String value) {
        ZSetOperations<String, String> zset = stringRedisTemplate.opsForZSet();
        return zset.rank(key, value);
    }

    /**
     * 有序集合获取排名
     */
    public Set<ZSetOperations.TypedTuple<String>> zRankWithScore(String key, long start, long end) {
        ZSetOperations<String, String> zset = stringRedisTemplate.opsForZSet();
        return zset.rangeWithScores(key, start, end);
    }

    /**
     * 有序集合添加
     */
    public Double zSetScore(String key, Object value) {
        ZSetOperations<String, String> zset = stringRedisTemplate.opsForZSet();
        return zset.score(key, value);
    }


    /**
     * 有序集合添加分数
     */
    public void incrementScore(String key, String value, double scoure) {
        ZSetOperations<String, String> zset = stringRedisTemplate.opsForZSet();
        zset.incrementScore(key, value, scoure);
    }


    /**
     * 有序集合获取排名
     */
    public Set<ZSetOperations.TypedTuple<String>> reverseZRankWithScore(String key, long start, long end) {
        ZSetOperations<String, String> zset = stringRedisTemplate.opsForZSet();
        return zset.reverseRangeByScoreWithScores(key, start, end);
    }

    /**
     * 有序集合获取排名
     */
    public Set<ZSetOperations.TypedTuple<String>> reverseZRankWithRank(String key, long start, long end) {
        ZSetOperations<String, String> zset = stringRedisTemplate.opsForZSet();
        return zset.reverseRangeWithScores(key, start, end);
    }

}
