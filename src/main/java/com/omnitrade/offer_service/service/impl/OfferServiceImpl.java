package com.omnitrade.offer_service.service.impl;

import com.omnitrade.offer_service.exception.BadRequestException;
import com.omnitrade.offer_service.exception.ResourceNotFoundException;
import com.omnitrade.offer_service.exception.UnauthorizedException;
import com.omnitrade.offer_service.kafka.producer.OfferEventProducer;
import com.omnitrade.offer_service.mapper.OfferMapper;
import com.omnitrade.offer_service.model.dto.ItemResponse;
import com.omnitrade.offer_service.model.dto.request.CreateOfferRequest;
import com.omnitrade.offer_service.model.dto.request.UpdateOfferRequest;
import com.omnitrade.offer_service.model.dto.response.OfferResponse;
import com.omnitrade.offer_service.model.entity.Offer;
import com.omnitrade.offer_service.model.entity.OfferItem;
import com.omnitrade.offer_service.model.entity.OfferStatusHistory;
import com.omnitrade.offer_service.model.enums.OfferStatus;
import com.omnitrade.offer_service.model.enums.OfferType;
import com.omnitrade.offer_service.repository.OfferItemRepository;
import com.omnitrade.offer_service.repository.OfferRepository;
import com.omnitrade.offer_service.repository.OfferStatusHistoryRepository;
import com.omnitrade.offer_service.service.ItemServiceClient;
import com.omnitrade.offer_service.service.OfferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferItemRepository offerItemRepository;
    private final OfferStatusHistoryRepository historyRepository;

    private final OfferMapper mapper;

    private final OfferEventProducer eventProducer;

    private final ItemServiceClient itemServiceClient;

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "offers", allEntries = true),
            @CacheEvict(value = "myOffers", allEntries = true),
            @CacheEvict(value = "receivedOffers", allEntries = true)
    })
    public OfferResponse createOffer(CreateOfferRequest request) {

        log.info("Creating offer for item {}", request.itemId());

        UUID buyerId = getCurrentUserId();

        ItemResponse item = itemServiceClient.getItem(request.itemId());

        if (item == null) {
            throw new ResourceNotFoundException("Item not found");
        }

        if (buyerId.equals(item.getSellerId())) {
            throw new BadRequestException("You cannot make an offer on your own item.");
        }

        if (request.offerType() != OfferType.CASH &&
                Boolean.FALSE.equals(item.getAllowTrade())) {

            throw new BadRequestException("Item does not allow trades.");
        }

        validateOffer(request);

        Offer offer = Offer.builder()
                .itemId(request.itemId())
                .sellerId(item.getSellerId())
                .buyerId(buyerId)
                .offerType(request.offerType())
                .cashAmount(request.cashAmount())
                .message(request.message())
                .status(OfferStatus.PENDING)
                .build();

        Offer savedOffer = offerRepository.save(offer);

        if (request.offeredItems() != null) {

            request.offeredItems().forEach(itemId -> {

                OfferItem offerItem = OfferItem.builder()
                        .offerId(savedOffer.getId())
                        .itemId(itemId)
                        .build();

                offerItemRepository.save(offerItem);
            });
        }

        historyRepository.save(
                OfferStatusHistory.builder()
                        .offerId(savedOffer.getId())
                        .status(OfferStatus.PENDING)
                        .changedBy(buyerId)
                        .build()
        );

        eventProducer.publishOfferCreated(
                mapper.toCreatedEvent(savedOffer)
        );

        log.info("Offer {} created successfully.", savedOffer.getId());

        return mapper.toResponse(savedOffer);
    }

    @Override
    public OfferResponse updateOffer(UpdateOfferRequest request) {
        return null;
    }

    @Override
    public OfferResponse getOfferById(UUID offerId) {
        return null;
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "offers", key = "#offerId"),
            @CacheEvict(value = "myOffers", allEntries = true),
            @CacheEvict(value = "receivedOffers", allEntries = true)
    })
    public void cancelOffer(UUID offerId) {

        UUID userId = getCurrentUserId();

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Offer not found"));

        if (!offer.getBuyerId().equals(userId)) {
            throw new UnauthorizedException("You cannot cancel this offer.");
        }

        if (offer.getStatus() != OfferStatus.PENDING) {
            throw new BadRequestException("Offer cannot be cancelled.");
        }

        offer.setStatus(OfferStatus.CANCELLED);

        offerRepository.save(offer);

        historyRepository.save(
                OfferStatusHistory.builder()
                        .offerId(offerId)
                        .status(OfferStatus.CANCELLED)
                        .changedBy(userId)
                        .build()
        );

        eventProducer.publishOfferCancelled(
                mapper.toCancelledEvent(offer)
        );

        log.info("Offer {} cancelled.", offerId);
    }

    @Override
    public OfferResponse acceptOffer(UUID offerId) {
        return null;
    }

    @Override
    public OfferResponse rejectOffer(UUID offerId) {
        return null;
    }

    @Override
    public OfferResponse completeOffer(UUID offerId) {
        return null;
    }

    @Override
    public OfferResponse expireOffer(UUID offerId) {
        return null;
    }

    @Override
    public Page<OfferResponse> getMyOffers(Pageable pageable) {
        return null;
    }

    @Override
    public Page<OfferResponse> getReceivedOffers(Pageable pageable) {
        return null;
    }

    @Override
    public Page<OfferResponse> getOffersForItem(UUID itemId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OfferResponse> getOffersByStatus(String status, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OfferResponse> getOffersByType(String type, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OfferResponse> getOffersByPriceRange(BigDecimal min, BigDecimal max, Pageable pageable) {
        return null;
    }

    private UUID getCurrentUserId() {

        return UUID.fromString(
                "123e4567-e89b-12d3-a456-426614174000"
        );
    }

    private void validateOffer(CreateOfferRequest request) {

        switch (request.offerType()) {

            case CASH -> {

                if (request.cashAmount() == null ||
                        request.cashAmount().signum() <= 0) {

                    throw new BadRequestException(
                            "Cash amount is required.");
                }
            }

            case TRADE -> {

                if (request.offeredItems() == null ||
                        request.offeredItems().isEmpty()) {

                    throw new BadRequestException(
                            "Trade items are required.");
                }
            }

            case CASH_AND_TRADE -> {

                if (request.cashAmount() == null ||
                        request.cashAmount().signum() <= 0) {

                    throw new BadRequestException(
                            "Cash amount is required.");
                }

                if (request.offeredItems() == null ||
                        request.offeredItems().isEmpty()) {

                    throw new BadRequestException(
                            "Trade items are required.");
                }
            }
        }
    }
}
