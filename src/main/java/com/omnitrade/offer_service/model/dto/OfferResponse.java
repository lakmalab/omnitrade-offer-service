package com.omnitrade.offer_service.model.dto.response;

import com.omnitrade.offer_service.model.dto.OfferItemResponse;
import com.omnitrade.offer_service.model.enums.OfferStatus;
import com.omnitrade.offer_service.model.enums.OfferType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record OfferResponse(

        UUID id,

        UUID itemId,

        UUID buyerId,

        UUID sellerId,

        OfferType offerType,

        BigDecimal cashAmount,

        List<OfferItemResponse> offeredItems,

        String message,

        OfferStatus status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}