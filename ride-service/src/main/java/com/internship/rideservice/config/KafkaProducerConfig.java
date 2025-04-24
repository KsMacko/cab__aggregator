package com.internship.rideservice.config;

import com.internship.commonevents.event.CashConfirmationRequest;
import com.internship.commonevents.event.RideNotificationEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Bean
    public ProducerFactory<String, CashConfirmationRequest> cashConfirmationProducerFactory() {
        Map<String, Object> configProps = collectPropsForProducerFactory();
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, CashConfirmationRequest> cashConfirmationKafkaTemplate() {
        return new KafkaTemplate<>(cashConfirmationProducerFactory());
    }
    @Bean
    public ProducerFactory<String, RideNotificationEvent> rideNotificationProducerFactory() {
        Map<String, Object> configProps = collectPropsForProducerFactory();
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, RideNotificationEvent> rideNotificationKafkaTemplate() {
        return new KafkaTemplate<>(rideNotificationProducerFactory());
    }
    private Map<String, Object> collectPropsForProducerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProps;
    }
}