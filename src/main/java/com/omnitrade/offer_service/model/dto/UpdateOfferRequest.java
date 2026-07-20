package com.omnitrade.offer_service.model.dto.request;

import com.omnitrade.offer_service.model.enums.OfferType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOfferRequest {

    @NotNull
    private UUID id;

    @NotNull
    private OfferType offerType;

    @PositiveOrZero
    private BigDecimal cashAmount;

    private List<UUID> offeredItems;

    @Size(max = 1000)
    private String message;

}