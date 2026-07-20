package com.omnitrade.offer_service.model.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CounterOfferRequest(

        @PositiveOrZero
        BigDecimal cashAmount,

        List<UUID> offeredItems,

        @Size(max = 1000)
        String message

) {}