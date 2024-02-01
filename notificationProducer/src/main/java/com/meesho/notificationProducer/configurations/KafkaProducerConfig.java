package com.meesho.notificationProducer.configurations;

import com.meesho.notificationProducer.constants.Constants;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private static final String BOOTSTRAP_ADDRESS = Constants.KAFKA_HOST;

    @Bean
    private Map<String, Object> generateConfigs(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG      , BOOTSTRAP_ADDRESS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG   , StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , StringSerializer.class);

        return configs;
    }

    @Bean
    private ProducerFactory<String, String> kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(generateConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> KafkaTemplate() {
        return new KafkaTemplate<>(kafkaProducerFactory());
    }
}
