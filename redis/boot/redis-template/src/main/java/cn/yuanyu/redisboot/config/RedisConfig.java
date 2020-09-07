package cn.yuanyu.redisboot.config;

import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisConfig {

    //@Bean
    //public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
    //    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    //    redisTemplate.setConnectionFactory(factory);
    //
    //    RedisSerializer<String> redisSerializer = new StringRedisSerializer();
    //    // key序列化
    //    redisTemplate.setKeySerializer(redisSerializer);
    //    // value序列化
    //    redisTemplate.setValueSerializer(redisSerializer);
    //    // value hashmap序列化
    //    redisTemplate.setHashKeySerializer(redisSerializer);
    //    // key hashmap序列化
    //    redisTemplate.setHashValueSerializer(redisSerializer);
    //
    //    return redisTemplate;
    //}
}
