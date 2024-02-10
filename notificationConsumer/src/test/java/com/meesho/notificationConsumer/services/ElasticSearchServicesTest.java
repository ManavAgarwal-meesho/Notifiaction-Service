package com.meesho.notificationConsumer.services;

import com.meesho.notificationConsumer.models.ESDocument;
import com.meesho.notificationConsumer.repository.ElasticSearchRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElasticSearchServicesTest {

    @Mock
    private ElasticSearchRepository esRepository;

    @InjectMocks
    private ElasticSearchServices elasticSearchServices;

    private static ESDocument mockData;
    @BeforeAll
    static void initializeTesting() {
        mockData = ESDocument.builder()
                .id("952ed318-e7fc-49a0-9e8b-dce8a1025af1")
                .phoneNumber("1234567890")
                .message("mock data")
                .createdAt(new Date())
                .build();
    }

    @Test
    void testIndexToElasticSearchDB_Success() {

        when(esRepository.save(any(ESDocument.class))).thenReturn(mockData);

        boolean result = elasticSearchServices.indexToElasticSearchDB(mockData);

        assertTrue(result);
        verify(esRepository, times(1)).save(mockData);
    }

    @Test
    void testIndexToElasticSearchDB_Failure() {

        doThrow(new RuntimeException("Test exception")).when(esRepository).save(any(ESDocument.class));

        boolean result = elasticSearchServices.indexToElasticSearchDB(mockData);

        assertFalse(result);
        verify(esRepository, times(1)).save(mockData);
    }
}
