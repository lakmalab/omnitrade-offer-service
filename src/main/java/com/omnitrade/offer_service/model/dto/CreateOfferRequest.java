package com.omnitrade.offer_service.model.dto.request;

import com.omnitrade.offer_service.model.enums.OfferType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOfferRequest(

        @NotNull(message = "Item is required")
        UUID itemId,

        @NotNull(message = "Offer type is required")
        OfferType offerType,

        @PositiveOrZero(message = "Cash amount cannot be negative")
        BigDecimal cashAmount,

        List<UUID> offeredItems,

        @Size(max = 1000, message = "Message cannot exceed 1000 characters")
        String message

) {}