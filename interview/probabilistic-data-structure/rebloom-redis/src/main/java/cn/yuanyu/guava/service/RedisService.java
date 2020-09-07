package cn.yuanyu.guava.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    /**
     * 布隆过滤器添加
     *
     * @param name  键
     * @param value 值
     */
    public Boolean bloomFilterAdd(String name, int value) {
        DefaultRedisScript<Boolean> bloomAdd = new DefaultRedisScript<>();
        bloomAdd.setScriptSource(new ResourceScriptSource(new ClassPathResource("bloomFilterAdd.lua")));
        bloomAdd.setResultType(Boolean.class);

        List<String> keyList = new ArrayList<>();
        keyList.add(name);
        keyList.add(value + "");
        return stringRedisTemplate.execute(bloomAdd, keyList);
    }

    /**
     * 布隆过滤器判断是否存在
     *
     * @param name  键
     * @param value 值
     */
    public Boolean bloomFilterExists(String name, int value) {
        DefaultRedisScript<Boolean> bloomExists = new DefaultRedisScript<>();
        bloomExists.setScriptSource(new ResourceScriptSource(new ClassPathResource("bloomFilterExists.lua")));
        bloomExists.setResultType(Boolean.class);

        List<String> keyList = new ArrayList<>();
        keyList.add(name);
        keyList.add(value + "");
        return stringRedisTemplate.execute(bloomExists, keyList);
    }

}
