package com.meesho.notificationConsumer.services;

import com.meesho.notificationConsumer.constants.Constants;
import com.meesho.notificationConsumer.repository.BlacklistRedisRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedisCacheServicesTest {

    @Mock
    private BlacklistRedisRepository redisBlacklistRepository;

    @InjectMocks
    private RedisCacheServices redisCacheServices;

    private static String phoneNumber;
    private static String referenceKey;

    @BeforeAll
    static void initializeTesting() {
        phoneNumber = "8384031249";
        referenceKey = Constants.HASH_REFERENCE + "_" + phoneNumber;
    }

    @Test
    void testCheckIfBlacklisted_WhenPhoneNumberIsBlacklisted() {

        when(redisBlacklistRepository.isPresent(referenceKey)).thenReturn(true);

        boolean result = redisCacheServices.checkIfBlacklisted(phoneNumber);

        assertTrue(result, "The phone number should be blacklisted");
    }

    @Test
    void testCheckIfBlacklisted_WhenPhoneNumberIsNotBlacklisted() {

        when(redisBlacklistRepository.isPresent(referenceKey)).thenReturn(false);

        boolean result = redisCacheServices.checkIfBlacklisted(phoneNumber);

        assertFalse(result, "The phone number should not be blacklisted");
    }
}
