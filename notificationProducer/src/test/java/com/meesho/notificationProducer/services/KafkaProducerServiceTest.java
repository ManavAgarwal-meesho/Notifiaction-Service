package com.meesho.notificationProducer.services;

import com.meesho.notificationProducer.constants.Constants;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService kafkaProducerService;

    private static String requestId;

    @BeforeAll
    static void initializeTesting() {
        requestId = "952ed318-e7fc-49a0-9e8b-xxxxxxxxxxxx";
    }

    @Test
    void testPublishToTopic_Success() throws ExecutionException, InterruptedException {

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(Constants.KAFKA_TOPIC, requestId);

        CompletableFuture<SendResult<String, String>> brokerResponse = new CompletableFuture<>();
        brokerResponse.complete(new SendResult<>(producerRecord, null));

        when(kafkaTemplate.send(anyString(), anyString())).thenReturn(brokerResponse);

        kafkaProducerService.publishToTopic(requestId);

        verify(kafkaTemplate, times(1)).send(eq(Constants.KAFKA_TOPIC), eq(requestId));
    }

    @Test
    void testPublishToTopic_Failure() throws ExecutionException, InterruptedException {

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(Constants.KAFKA_TOPIC, requestId);

        CompletableFuture<SendResult<String, String>> brokerResponse = new CompletableFuture<>();
        brokerResponse.completeExceptionally(new RuntimeException("Error sending message"));

        when(kafkaTemplate.send(anyString(), anyString())).thenReturn(brokerResponse);

        kafkaProducerService.publishToTopic(requestId);

        verify(kafkaTemplate, times(1)).send(eq(Constants.KAFKA_TOPIC), eq(requestId));
    }

}
