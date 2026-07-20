package com.omnitrade.offer_service.kafka.event;

import com.omnitrade.offer_service.model.enums.OfferType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record OfferCreatedEvent(

        UUID offerId,

        UUID itemId,

        UUID buyerId,

        UUID sellerId,

        OfferType offerType,

        BigDecimal cashAmount,

        List<UUID> offeredItems,

        String message,

        LocalDateTime createdAt

) {}