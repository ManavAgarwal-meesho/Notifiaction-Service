package com.meesho.notificationProducer.repository.implementation;

import com.meesho.notificationProducer.constants.Constants;
import com.meesho.notificationProducer.models.BlacklistRedis;
import com.meesho.notificationProducer.repository.BlacklistRedisRepository;
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
    public Boolean addToBlacklist(BlacklistRedis blk) {
        return redisOperations.putIfAbsent(HASH_REFERENCE, blk.getId(), blk);
    }

    /*  referenceKey = HASH_REFERENCE +"_" + blacklistNumber    */
    @Override
    public void removeFromBlacklist(String referenceKey) {
        redisOperations.delete(HASH_REFERENCE, referenceKey);
    }

    @Override
    public Boolean isPresent(String referenceKey) {
        return redisOperations.hasKey(HASH_REFERENCE, referenceKey);
    }

    @Override
    public Map<String, BlacklistRedis> getAllBlacklistContact() {
        return redisOperations.entries(HASH_REFERENCE);
    }

}
