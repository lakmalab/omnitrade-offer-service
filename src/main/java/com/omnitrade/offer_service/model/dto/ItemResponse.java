package com.omnitrade.offer_service.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ItemResponse {

    private UUID id;

    private UUID sellerId;

    private UUID categoryId;

    private String title;

    private BigDecimal price;

    private String status;

    private Boolean allowTrade;
}
