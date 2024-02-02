package com.meesho.notificationConsumer.repository.implementation;

import com.meesho.notificationConsumer.constants.Constants;
import com.meesho.notificationConsumer.models.BlacklistRedis;
import com.meesho.notificationConsumer.repository.BlacklistRedisRepository;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BlacklistRedisRepositoryImplementation implements BlacklistRedisRepository {

    private static final String HASH_REFERENCE = Constants.HASH_REFERENCE;
    @Resource(name="redisTemplate")
    private HashOperations<String, String, BlacklistRedis> redisOperations;

    @Override
    public Boolean isPresent(String referenceKey) {
        return redisOperations.hasKey(HASH_REFERENCE, referenceKey);
    }
}
