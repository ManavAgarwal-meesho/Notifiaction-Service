package com.meesho.notificationProducer.services.storageServicesTest;

import com.meesho.notificationProducer.models.RequestDatabase;
import com.meesho.notificationProducer.repository.RequestDatabaseRepository;
import com.meesho.notificationProducer.services.storageServices.SQLDatabaseServices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SQLDatabaseServicesTest {

    @Mock
    private RequestDatabaseRepository databaseRepository;

    @InjectMocks
    private SQLDatabaseServices sqlDatabaseServices;

    private static RequestDatabase mockData;
    private static String requestId;
    private static String invalidRequestId;

    @BeforeAll
    static void initializeTesting() {
        requestId = "952ed318-e7fc-49a0-9e8b-dce8a1025af1";
        mockData = RequestDatabase.builder()
                .requestId(requestId)
                .message("mock data")
                .phoneNumber("1234567890")
                .build();
        invalidRequestId = "invalid";
    }
    @Test
    void testStoreData() {

        when(databaseRepository.save(any(RequestDatabase.class))).thenReturn(mockData);

        String storedRequestId = sqlDatabaseServices.storeData(mockData);

        assertEquals(requestId, storedRequestId);
        verify(databaseRepository, times(1)).save(any(RequestDatabase.class));
    }

    @Test
    void testFetchById() {

        when(databaseRepository.findById(requestId)).thenReturn(Optional.of(mockData));

        Optional<RequestDatabase> fetchedData = sqlDatabaseServices.fetchById(requestId);

        assertTrue(fetchedData.isPresent());
        assertEquals(mockData, fetchedData.get());
        verify(databaseRepository, times(1)).findById(requestId);
    }

    @Test
    void testFetchById_NotFound() {

        when(databaseRepository.findById(invalidRequestId)).thenReturn(Optional.empty());

        Optional<RequestDatabase> fetchedData = sqlDatabaseServices.fetchById(invalidRequestId);

        assertTrue(fetchedData.isEmpty());
        verify(databaseRepository, times(1)).findById(invalidRequestId);
    }
}