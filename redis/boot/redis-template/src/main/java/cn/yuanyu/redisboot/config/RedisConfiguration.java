package cn.yuanyu.redisboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//docker run \
//--name redis \
//-p 6379:6379  \
//-di redis \
//--requirepass "admin"

/**
 * @author yuanyu
 */
@Configuration
public class RedisConfiguration<V> {
    @Bean
    RedisTemplate<String, V> getRedisTemplate(RedisTemplate<String, V> redisTemplate) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
