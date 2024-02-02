package com.meesho.notificationConsumer.repository;


import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistRedisRepository {
    Boolean isPresent(String phoneNumber);
}
