package com.meesho.notificationConsumer.services;

import com.meesho.notificationConsumer.constants.Constants;
import com.meesho.notificationConsumer.models.RequestDatabase;
import com.meesho.notificationConsumer.repository.RequestDatabaseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ThirdPartyAPIServiceTest {

    @Mock
    private RequestDatabaseRepository requestDatabaseRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ThirdPartyAPIService thirdPartyAPIService;

    private static String apiKey;
    private static String apiUrl;
    private static String requestId;
    private static String phoneNumber;
    private static String message;
    private static RequestDatabase mockData;

    @BeforeAll
    static void initializeTesting() {
        apiKey = "test-api-key";
        apiUrl = "test-api-url";
        requestId = "952ed318-e7fc-49a0-9e8b-dce8a1025af1";
        phoneNumber = "8384031249";
        message = "mock data";

        mockData = RequestDatabase.builder()
                .requestId(requestId)
                .createdAt(new Date())
                .message(message)
                .phoneNumber(phoneNumber)
                .updatedAt(new Date())
                .build();
    }


    @Test
    void testSendSMSAPI_Successful() {

        when(restTemplate.postForObject(eq(apiUrl), any(HttpEntity.class), eq(Object.class))).thenReturn("SuccessResponse");

        Constants.THIRD_PARTY_API_KEY = apiKey;
        Constants.THIRD_PARTY_API = apiUrl;

        boolean result = thirdPartyAPIService.sendSMSAPI(mockData);

        assertTrue(result);
        verify(restTemplate, times(1)).postForObject(eq(apiUrl), any(HttpEntity.class), eq(Object.class));
    }

    @Test
    void testSendSMSAPI_Failed() {

        when(restTemplate.postForObject(eq(apiUrl), any(HttpEntity.class), eq(Object.class))).thenThrow(new RuntimeException("API Error"));
        Constants.THIRD_PARTY_API_KEY = apiKey;
        Constants.THIRD_PARTY_API = apiUrl;

        boolean result = thirdPartyAPIService.sendSMSAPI(mockData);

        assertFalse(result);
        verify(restTemplate, times(1)).postForObject(eq(apiUrl), any(HttpEntity.class), eq(Object.class));
    }
}
