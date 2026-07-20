package com.omnitrade.offer_service.service;


import com.omnitrade.offer_service.model.dto.ItemResponse;

import java.util.UUID;

public interface ItemServiceClient {

    ItemResponse getItem(UUID itemId);

    boolean isOwner(UUID itemId);

    boolean isTradable(UUID itemId);

}