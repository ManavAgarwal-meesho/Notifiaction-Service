package com.meesho.notificationProducer.configurations;

import com.meesho.notificationProducer.models.BlacklistRedis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, BlacklistRedis> redisTemplate(){
        RedisTemplate<String, BlacklistRedis> blacklistDBRedisTemplate = new RedisTemplate<>();
        blacklistDBRedisTemplate.setConnectionFactory(redisConnectionFactory());

        return blacklistDBRedisTemplate;
    }
}
