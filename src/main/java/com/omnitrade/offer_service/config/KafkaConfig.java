package com.omnitrade.offer_service.config;

import com.omnitrade.offer_service.config.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic offerCreatedTopic() {
        return new NewTopic(KafkaTopics.OFFER_CREATED, 3, (short) 1);
    }

    @Bean
    NewTopic offerUpdatedTopic() {
        return new NewTopic(KafkaTopics.OFFER_UPDATED, 3, (short) 1);
    }

    @Bean
    NewTopic offerAcceptedTopic() {
        return new NewTopic(KafkaTopics.OFFER_ACCEPTED, 3, (short) 1);
    }

    @Bean
    NewTopic offerRejectedTopic() {
        return new NewTopic(KafkaTopics.OFFER_REJECTED, 3, (short) 1);
    }

    @Bean
    NewTopic offerCancelledTopic() {
        return new NewTopic(KafkaTopics.OFFER_CANCELLED, 3, (short) 1);
    }

    @Bean
    NewTopic offerCompletedTopic() {
        return new NewTopic(KafkaTopics.OFFER_COMPLETED, 3, (short) 1);
    }

}