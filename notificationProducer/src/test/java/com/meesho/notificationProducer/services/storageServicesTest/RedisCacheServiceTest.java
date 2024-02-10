package com.meesho.notificationProducer.services.storageServicesTest;

import com.meesho.notificationProducer.models.BlacklistRedis;
import com.meesho.notificationProducer.repository.BlacklistRedisRepository;
import com.meesho.notificationProducer.services.storageServices.RedisCacheServices;
import com.meesho.notificationProducer.utils.PhoneNumberValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RedisCacheServiceTest {

    @Mock
    private BlacklistRedisRepository redisBlacklistRepository;

    @InjectMocks
    private RedisCacheServices redisCacheServices;

    private static String phoneNumber;
    private static String altPhoneNumber;
    private static String invalidPhoneNumber;
    private static List<String> phoneNumbers;
    private static List<String> invalidPhoneNumbers;

    @BeforeAll
    static void initializeTesting() {
        phoneNumber = "8384031249";
        altPhoneNumber = "1234567890";
        invalidPhoneNumber = "invalid";

        phoneNumbers = Arrays.asList(altPhoneNumber, phoneNumber);
        invalidPhoneNumbers = Arrays.asList(invalidPhoneNumber, phoneNumber);
    }

    @Test
    void testAddNumbersToBlacklist() {

        when(redisBlacklistRepository.isPresent(anyString())).thenReturn(false);
        when(redisBlacklistRepository.addToBlacklist(any(BlacklistRedis.class))).thenReturn(true);

        boolean result = redisCacheServices.addNumbersToBlacklist(phoneNumbers);

        assertTrue(result);
        verify(redisBlacklistRepository, times(2)).isPresent(anyString());
        verify(redisBlacklistRepository, times(2)).addToBlacklist(any(BlacklistRedis.class));
    }

    @Test
    void testAddNumbersToBlacklist_AlreadyBlacklisted() {

        when(redisBlacklistRepository.isPresent(anyString())).thenReturn(true);

        boolean result = redisCacheServices.addNumbersToBlacklist(phoneNumbers);

        assertTrue(result);
        verify(redisBlacklistRepository, times(2)).isPresent(anyString());
        verify(redisBlacklistRepository, times(0)).addToBlacklist(any(BlacklistRedis.class));
    }

    @Test
    void testCheckIfBlacklisted() {

        when(redisBlacklistRepository.isPresent(anyString())).thenReturn(true);

        boolean result = redisCacheServices.checkIfBlacklisted(phoneNumber);

        assertTrue(result);
        verify(redisBlacklistRepository, times(1)).isPresent(anyString());
    }

    @Test
    void testRemoveNumbersFromBlacklist() {

        when(redisBlacklistRepository.isPresent(anyString())).thenReturn(true);
        doNothing().when(redisBlacklistRepository).removeFromBlacklist(anyString());

        redisCacheServices.removeNumbersFromBlacklist(phoneNumbers);

        verify(redisBlacklistRepository, times(2)).isPresent(anyString());
        verify(redisBlacklistRepository, times(2)).removeFromBlacklist(anyString());
    }

    @Test
    void testRemoveNumbersFromBlacklist_InvalidPhoneNumber() {

        when(redisBlacklistRepository.isPresent(anyString())).thenReturn(true);
        doNothing().when(redisBlacklistRepository).removeFromBlacklist(anyString());

        // Act
        redisCacheServices.removeNumbersFromBlacklist(invalidPhoneNumbers);

        // Assert
        verify(redisBlacklistRepository, times(1)).isPresent(anyString());
        verify(redisBlacklistRepository, times(1)).removeFromBlacklist(anyString());
    }

    @Test
    void testRemoveNumbersFromBlacklist_NotBlacklisted() {

        when(redisBlacklistRepository.isPresent(anyString())).thenReturn(false);

        redisCacheServices.removeNumbersFromBlacklist(phoneNumbers);

        verify(redisBlacklistRepository, times(2)).isPresent(anyString());
        verify(redisBlacklistRepository, times(0)).removeFromBlacklist(anyString());
    }

    @Test
    void testGetAllBlacklistedNumbers() {

        Map<String, BlacklistRedis> mockBlacklist = new HashMap<>();
        mockBlacklist.put("key1", new BlacklistRedis("key1", altPhoneNumber));
        mockBlacklist.put("key2", new BlacklistRedis("key2", phoneNumber));

        when(redisBlacklistRepository.getAllBlacklistContact()).thenReturn(mockBlacklist);

        List<String> result = redisCacheServices.getAllBlacklistedNumbers();

        assertEquals(phoneNumbers, result);
        verify(redisBlacklistRepository, times(1)).getAllBlacklistContact();
    }
}
