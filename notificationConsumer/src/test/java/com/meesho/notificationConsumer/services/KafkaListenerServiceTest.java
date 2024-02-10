package com.meesho.notificationConsumer.services;

import com.meesho.notificationConsumer.models.RequestDatabase;
import com.meesho.notificationConsumer.repository.RequestDatabaseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KafkaListenerServiceTest {

    @Mock
    private RequestDatabaseRepository databaseRepository;

    @Mock
    private SQLDatabaseServices sqlDatabaseServices;

    @Mock
    private RedisCacheServices redisCacheServices;

    @Mock
    private ThirdPartyAPIService thirdPartyAPIServices;

    @Mock
    private ElasticSearchServices ESServices;

    @InjectMocks
    private KafkaListenerService kafkaListenerService;

    private static String requestId;
    private static RequestDatabase mockData;
    private static String phoneNumber;
    private static String failureComment;
    @BeforeAll
    static void initializeTesting() {
        requestId = "952ed318-e7fc-49a0-9e8b-dce8a1025af1";
        phoneNumber = "8384031249";
        mockData = RequestDatabase.builder()
                .requestId(requestId)
                .phoneNumber(phoneNumber)
                .message("mock data")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        failureComment = "Error while sending SMS from Third party API with request Id : " + requestId;
    }

    @Test
    void testConsumeFromTopic_Success() throws IOException {

        when(databaseRepository.findById(requestId)).thenReturn(Optional.of(mockData));
        when(redisCacheServices.checkIfBlacklisted(phoneNumber)).thenReturn(false);
        when(thirdPartyAPIServices.sendSMSAPI(mockData)).thenReturn(true);
        when(sqlDatabaseServices.updateStatusOnSuccess(requestId)).thenReturn(true);
        when(ESServices.indexToElasticSearchDB(any())).thenReturn(true);

        kafkaListenerService.consumeFromTopic(requestId);

        verify(databaseRepository, times(1)).findById(requestId);
        verify(redisCacheServices, times(1)).checkIfBlacklisted(phoneNumber);
        verify(thirdPartyAPIServices, times(1)).sendSMSAPI(mockData);
        verify(ESServices, times(1)).indexToElasticSearchDB(any());
        verify(sqlDatabaseServices, times(1)).updateStatusOnSuccess(requestId);
    }

    @Test
    void testConsumeFromTopic_BlacklistedNumber() throws IOException {

        when(databaseRepository.findById(requestId)).thenReturn(Optional.of(mockData));
        when(redisCacheServices.checkIfBlacklisted(phoneNumber)).thenReturn(true);

        kafkaListenerService.consumeFromTopic(requestId);

        verify(databaseRepository, times(1)).findById(requestId);
        verify(redisCacheServices, times(1)).checkIfBlacklisted(phoneNumber);
        verify(thirdPartyAPIServices, never()).sendSMSAPI(any());
        verify(ESServices, never()).indexToElasticSearchDB(any());
        verify(sqlDatabaseServices, never()).updateStatusOnSuccess(requestId);
        verify(sqlDatabaseServices, times(1)).updateStatusOnFailure(requestId, "Consumer Phone Number ("+phoneNumber+") is blacklisted");
    }

    @Test
    void testConsumeFromTopic_ThirdPartyAPIFailure() throws IOException {

        when(databaseRepository.findById(requestId)).thenReturn(Optional.of(mockData));
        when(redisCacheServices.checkIfBlacklisted(phoneNumber)).thenReturn(false);
        when(thirdPartyAPIServices.sendSMSAPI(mockData)).thenReturn(false);
        when(sqlDatabaseServices.updateStatusOnFailure(requestId, failureComment)).thenReturn(true);

        kafkaListenerService.consumeFromTopic(requestId);

        verify(databaseRepository, times(1)).findById(requestId);
        verify(redisCacheServices, times(1)).checkIfBlacklisted(phoneNumber);
        verify(thirdPartyAPIServices, times(1)).sendSMSAPI(mockData);
        verify(ESServices, never()).indexToElasticSearchDB(any());
        verify(sqlDatabaseServices, never()).updateStatusOnSuccess(requestId);
        verify(sqlDatabaseServices, times(1)).updateStatusOnFailure(requestId, failureComment);
    }
}
