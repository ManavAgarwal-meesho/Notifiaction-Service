package com.meesho.notificationConsumer.models;

import com.meesho.notificationConsumer.constants.Constants;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

@RedisHash(Constants.HASH_REFERENCE)
public class BlacklistRedis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String phoneNumber;
}
