package com.omnitrade.offer_service.service;

import com.omnitrade.offer_service.model.dto.request.CreateOfferRequest;
import com.omnitrade.offer_service.model.dto.request.UpdateOfferRequest;
import com.omnitrade.offer_service.model.dto.response.OfferResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface OfferService {

    OfferResponse createOffer(CreateOfferRequest request);

    OfferResponse updateOffer(UpdateOfferRequest request);

    OfferResponse getOfferById(UUID offerId);

    void cancelOffer(UUID offerId);

    OfferResponse acceptOffer(UUID offerId);

    OfferResponse rejectOffer(UUID offerId);

    OfferResponse completeOffer(UUID offerId);

    OfferResponse expireOffer(UUID offerId);

    Page<OfferResponse> getMyOffers(Pageable pageable);

    Page<OfferResponse> getReceivedOffers(Pageable pageable);

    Page<OfferResponse> getOffersForItem(UUID itemId, Pageable pageable);

    Page<OfferResponse> getOffersByStatus(String status, Pageable pageable);

    Page<OfferResponse> getOffersByType(String type, Pageable pageable);

    Page<OfferResponse> getOffersByPriceRange(
            BigDecimal min,
            BigDecimal max,
            Pageable pageable
    );

}