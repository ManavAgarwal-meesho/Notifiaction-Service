package com.meesho.notificationProducer.services;

import com.meesho.notificationProducer.constants.Constants;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private final static Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = Constants.KAFKA_TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishToTopic(String requestId) {
        logger.info(String.format("Sending message with id: \"%s\"", requestId));

        // handle the results asynchronously
        CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(TOPIC, requestId);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Sent message with id = [" + requestId + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                logger.error("Unable to send message with id = [" + requestId + "] due to : " + ex.getMessage());
            }
        });
    }
}
