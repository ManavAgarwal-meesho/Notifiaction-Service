package com.meesho.notificationProducer.repository;

import com.meesho.notificationProducer.models.BlacklistRedis;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BlacklistRedisRepository {
    void addToBlacklist(BlacklistRedis blk);

    void removeFromBlacklist(String phoneNumber);

    Boolean isPresent(String phoneNumber);

    Map<String, BlacklistRedis> getAllBlacklistContact();
}
