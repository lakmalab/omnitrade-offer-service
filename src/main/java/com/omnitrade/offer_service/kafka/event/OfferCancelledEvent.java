package com.omnitrade.offer_service.kafka.event;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record OfferCancelledEvent(

        UUID offerId,

        UUID cancelledBy,

        LocalDateTime cancelledAt

) {}
