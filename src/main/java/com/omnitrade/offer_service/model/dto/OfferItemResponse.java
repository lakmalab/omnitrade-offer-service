package com.omnitrade.offer_service.model.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OfferItemResponse(

        UUID id,

        UUID itemId

) {}