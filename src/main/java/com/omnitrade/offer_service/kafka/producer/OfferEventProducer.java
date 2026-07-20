package com.omnitrade.offer_service.kafka.producer;

import com.omnitrade.offer_service.config.KafkaTopics;
import com.omnitrade.offer_service.kafka.event.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOfferCreated(OfferCreatedEvent event) {

        kafkaTemplate.send(
                KafkaTopics.OFFER_CREATED,
                event.offerId().toString(),
                event
        );

        log.info("Published OFFER_CREATED {}", event.offerId());
    }

    public void publishOfferUpdated(OfferUpdatedEvent event) {

        kafkaTemplate.send(
                KafkaTopics.OFFER_UPDATED,
                event.offerId().toString(),
                event
        );

        log.info("Published OFFER_UPDATED {}", event.offerId());
    }

    public void publishOfferAccepted(OfferAcceptedEvent event) {

        kafkaTemplate.send(
                KafkaTopics.OFFER_ACCEPTED,
                event.offerId().toString(),
                event
        );

        log.info("Published OFFER_ACCEPTED {}", event.offerId());
    }

    public void publishOfferRejected(OfferRejectedEvent event) {

        kafkaTemplate.send(
                KafkaTopics.OFFER_REJECTED,
                event.offerId().toString(),
                event
        );

        log.info("Published OFFER_REJECTED {}", event.offerId());
    }

    public void publishOfferCancelled(OfferCancelledEvent event) {

        kafkaTemplate.send(
                KafkaTopics.OFFER_CANCELLED,
                event.offerId().toString(),
                event
        );

        log.info("Published OFFER_CANCELLED {}", event.offerId());
    }

    public void publishOfferCompleted(OfferCompletedEvent event) {

        kafkaTemplate.send(
                KafkaTopics.OFFER_COMPLETED,
                event.offerId().toString(),
                event
        );

        log.info("Published OFFER_COMPLETED {}", event.offerId());
    }

}