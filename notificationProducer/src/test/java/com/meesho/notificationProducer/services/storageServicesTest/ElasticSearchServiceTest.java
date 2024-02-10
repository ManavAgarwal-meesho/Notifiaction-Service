package com.meesho.notificationProducer.services.storageServicesTest;

import com.meesho.notificationProducer.models.ESDocument;
import com.meesho.notificationProducer.repository.ElasticSearchRepository;
import com.meesho.notificationProducer.services.storageServices.ElasticSearchServices;
import com.meesho.notificationProducer.utils.ConvertStringTimeToDates;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElasticSearchServiceTest {

    @Mock
    private ElasticSearchRepository esRepository;

    @InjectMocks
    private ElasticSearchServices elasticSearchServices;

    private static String keyword;
    private static Integer page;
    private static String phoneNumber;
    private static String startTime;
    private static String endTime;

    @BeforeAll
    static void initializeTesting() {
        keyword = "test";
        page = 0;
        phoneNumber = "1234567890";
        startTime = ConvertStringTimeToDates.convertStringTimestampToDate("22-01-2015 10:15:55 AM");
        endTime = ConvertStringTimeToDates.convertStringTimestampToDate("22-01-2015 05:15:55 PM");
    }
    @Test
    void testGetDocumentsByKeyword() throws IOException {

        List<ESDocument> mockDocuments = Collections.singletonList(new ESDocument());

        Page<ESDocument> mockPage = new PageImpl<>(mockDocuments);
        when(esRepository.findByMessageContaining(anyString(), any(PageRequest.class))).thenReturn(mockPage);

        List<ESDocument> result = elasticSearchServices.getDocumentsByKeyword(keyword, page);

        assertEquals(mockDocuments, result);
        verify(esRepository, times(1)).findByMessageContaining(eq(keyword), any(PageRequest.class));
    }

    @Test
    void testGetDocumentsByPhoneNumber() throws IOException {

        List<ESDocument> mockDocuments = Collections.singletonList(new ESDocument());

        Page<ESDocument> mockPage = new PageImpl<>(mockDocuments);
        when(esRepository.findByPhoneNumberAndCreatedAtBetween(anyString(), anyString(), anyString(), any(PageRequest.class)))
                .thenReturn(mockPage);

        List<ESDocument> result = elasticSearchServices.getDocumentsByPhoneNumber(phoneNumber, startTime, endTime, page);

        assertEquals(mockDocuments, result);
        verify(esRepository, times(1))
                .findByPhoneNumberAndCreatedAtBetween(eq(startTime), eq(endTime), eq(phoneNumber), any(PageRequest.class));
    }

    @Test
    void testGetDocumentsByKeyword_Error() {

        when(esRepository.findByMessageContaining(anyString(), any(PageRequest.class))).thenThrow(new RuntimeException("Test error"));

        assertThrows(IOException.class, () -> elasticSearchServices.getDocumentsByKeyword(keyword, page));
        verify(esRepository, times(1)).findByMessageContaining(eq(keyword), any(PageRequest.class));
    }

    @Test
    void testGetDocumentsByPhoneNumber_Error() {

        when(esRepository.findByPhoneNumberAndCreatedAtBetween(anyString(), anyString(), anyString(), any(PageRequest.class)))
                .thenThrow(new RuntimeException("Test error"));

        assertThrows(IOException.class, () ->
                elasticSearchServices.getDocumentsByPhoneNumber(phoneNumber, startTime, endTime, page));
        verify(esRepository, times(1))
                .findByPhoneNumberAndCreatedAtBetween(eq(startTime), eq(endTime), eq(phoneNumber), any(PageRequest.class));
    }

    @Test
    void testFindAllDocuments() {

        List<ESDocument> mockDocuments = Collections.singletonList(new ESDocument());
        when(esRepository.findAll()).thenReturn(mockDocuments);

        Iterable<ESDocument> result = elasticSearchServices.findAllDocuments();

        assertEquals(mockDocuments, result);
        verify(esRepository, times(1)).findAll();
    }
}
