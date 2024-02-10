package com.meesho.notificationConsumer.services;
import com.meesho.notificationConsumer.constants.Constants;
import com.meesho.notificationConsumer.repository.RequestDatabaseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class SQLDatabaseServicesTest {

    @Mock
    private RequestDatabaseRepository databaseRepository;

    @InjectMocks
    private SQLDatabaseServices sqlDatabaseServices;

    private static String requestId;
    private static String failureComment;
    @BeforeAll
    static void initializeTesting() {
        requestId = "952ed318-e7fc-49a0-9e8b-dce8a1025af1";
        failureComment = "Failed to update status";
    }

    @Test
    void testUpdateStatusOnSuccess_SuccessfulUpdate() {

        doNothing().when(databaseRepository).updateRequest(requestId, Constants.DB_REQUEST_SUCCESSFUL, Constants.DB_REQUEST_SUCCESSFUL);

        boolean result = sqlDatabaseServices.updateStatusOnSuccess(requestId);

        assertTrue(result, "The status update should be successful");
    }

    @Test
    void testUpdateStatusOnSuccess_FailedUpdate() {

        doThrow(new RuntimeException(failureComment)).when(databaseRepository).updateRequest(requestId, Constants.DB_REQUEST_SUCCESSFUL, Constants.DB_REQUEST_SUCCESSFUL);

        boolean result = sqlDatabaseServices.updateStatusOnSuccess(requestId);

        assertFalse(result, "The status update should fail");
    }

    @Test
    void testUpdateStatusOnFailure_SuccessfulUpdate() {

        doNothing().when(databaseRepository).updateRequest(requestId, failureComment, Constants.DB_REQUEST_FAILURE);

        boolean result = sqlDatabaseServices.updateStatusOnFailure(requestId, failureComment);

        assertTrue(result, "The status update should be successful");
    }

    @Test
    void testUpdateStatusOnFailure_FailedUpdate() {

        doThrow(new RuntimeException(failureComment)).when(databaseRepository).updateRequest(requestId, failureComment, Constants.DB_REQUEST_FAILURE);

        boolean result = sqlDatabaseServices.updateStatusOnFailure(requestId, failureComment);

        assertFalse(result, "The status update should fail");
    }
}
