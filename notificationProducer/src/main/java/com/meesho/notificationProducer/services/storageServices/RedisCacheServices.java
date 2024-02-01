package com.meesho.notificationProducer.services.storageServices;

import com.meesho.notificationProducer.constants.Constants;
import com.meesho.notificationProducer.models.BlacklistRedis;
import com.meesho.notificationProducer.repository.BlacklistRedisRepository;
import com.meesho.notificationProducer.utils.PhoneNumberValidator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedisCacheServices {

    private static final String HASH_REFERENCE = Constants.HASH_REFERENCE;

    @Autowired
    private BlacklistRedisRepository redisBlacklistRepository;

    private final static Logger logger = LoggerFactory.getLogger(RedisCacheServices.class);

    // Add phone numbers to Blacklist
    public void addNumbersToBlacklist(@NotNull @NotEmpty List<String> phoneNumbers){

        for(String phoneNumber : phoneNumbers){

            phoneNumber = phoneNumber.trim();
            if(!PhoneNumberValidator.isPhoneNumberValid(phoneNumber) || Boolean.TRUE.equals(checkIfBlacklisted(phoneNumber))) {
                continue;
            }

            String referenceKey = HASH_REFERENCE + "_" + phoneNumber;

            BlacklistRedis blacklistRedisEntry = BlacklistRedis.builder()
                    .id(referenceKey)
                    .phoneNumber(phoneNumber)
                    .build();

            redisBlacklistRepository.addToBlacklist(blacklistRedisEntry);
            logger.info("New Number Blacklisted in Redis {}", phoneNumber);
        }

    }

    // Check if a number exists in blacklist
    public boolean checkIfBlacklisted(String phoneNumber){
        String referenceKey = HASH_REFERENCE + "_" + phoneNumber;
        return redisBlacklistRepository.isPresent(referenceKey);
    }

    // Remove phone numbers from blacklist
    public void removeNumbersFromBlacklist(@NotNull @NotEmpty List<String> phoneNumbers){

        for(String phoneNumber : phoneNumbers){

            phoneNumber = phoneNumber.trim();
            if(!PhoneNumberValidator.isPhoneNumberValid(phoneNumber) || Boolean.FALSE.equals(checkIfBlacklisted(phoneNumber))) {
                continue;
            }

            String referenceKey = HASH_REFERENCE + "_" + phoneNumber;
            redisBlacklistRepository.removeFromBlacklist(referenceKey);

            logger.info("Number removed from blacklist in Redis {}", phoneNumber);
        }
    }


    // Fetch All Blacklisted Numbers
    public List<String> getAllBlacklistedNumbers() {

        Map<String, BlacklistRedis> blacklistNumbers = redisBlacklistRepository.getAllBlacklistContact();
        List<String> blacklistRedisList = new ArrayList<>();

        for(Map.Entry<String, BlacklistRedis> mapRow : blacklistNumbers.entrySet()){
            blacklistRedisList.add(mapRow.getValue().getPhoneNumber());
        }

        return blacklistRedisList;

    }
}
