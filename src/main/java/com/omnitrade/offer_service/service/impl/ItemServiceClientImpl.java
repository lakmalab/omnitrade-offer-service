package com.omnitrade.offer_service.service.impl;

import com.omnitrade.offer_service.model.dto.ItemOwnershipResponse;
import com.omnitrade.offer_service.model.dto.ItemResponse;
import com.omnitrade.offer_service.service.ItemServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemServiceClientImpl implements ItemServiceClient {

    private final RestClient itemRestClient;

    @Override
    public ItemResponse getItem(UUID itemId) {

        return itemRestClient
                .get()
                .uri("/api/v1/items/{id}", itemId)
                .retrieve()
                .body(ItemResponse.class);
    }

    @Override
    public boolean isOwner(UUID itemId) {

        ItemOwnershipResponse response = itemRestClient
                .get()
                .uri("/api/v1/items/{id}/owner", itemId)
                .retrieve()
                .body(ItemOwnershipResponse.class);

        return Boolean.TRUE.equals(response.getOwner());
    }

    @Override
    public boolean isTradable(UUID itemId) {

        ItemResponse item = getItem(itemId);

        return Boolean.TRUE.equals(item.getAllowTrade());
    }

}