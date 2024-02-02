package com.meesho.notificationConsumer.services;

import com.meesho.notificationConsumer.constants.Constants;
import com.meesho.notificationConsumer.repository.BlacklistRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheServices {

    private static final String HASH_REFERENCE = Constants.HASH_REFERENCE;

    @Autowired
    private BlacklistRedisRepository redisBlacklistRepository;

    // Check if a number exists in blacklist
    public boolean checkIfBlacklisted(String phoneNumber){
        String referenceKey = HASH_REFERENCE + "_" + phoneNumber;
        return redisBlacklistRepository.isPresent(referenceKey);
    }
}
