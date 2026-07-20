package com.omnitrade.offer_service.controller;

import com.omnitrade.offer_service.service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<OfferResponse> createOffer(
            @Valid @RequestBody CreateOfferRequest request) {

        OfferResponse response = offerService.createOffer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{offerId}")
    public ResponseEntity<OfferResponse> updateOffer(
            @PathVariable UUID offerId,
            @Valid @RequestBody UpdateOfferRequest request) {

        request.setId(offerId);

        OfferResponse response = offerService.updateOffer(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponse> getOffer(
            @PathVariable UUID offerId) {

        return ResponseEntity.ok(offerService.getOfferById(offerId));
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<Void> cancelOffer(
            @PathVariable UUID offerId) {

        offerService.cancelOffer(offerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    public ResponseEntity<Page<OfferResponse>> getMyOffers(Pageable pageable) {

        return ResponseEntity.ok(
                offerService.getMyOffers(pageable)
        );
    }

    @GetMapping("/received")
    public ResponseEntity<Page<OfferResponse>> getReceivedOffers(Pageable pageable) {

        return ResponseEntity.ok(
                offerService.getReceivedOffers(pageable)
        );
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<Page<OfferResponse>> getOffersForItem(
            @PathVariable UUID itemId,
            Pageable pageable) {

        return ResponseEntity.ok(
                offerService.getOffersForItem(itemId, pageable)
        );
    }

    @PutMapping("/{offerId}/accept")
    public ResponseEntity<OfferResponse> acceptOffer(
            @PathVariable UUID offerId) {

        return ResponseEntity.ok(
                offerService.acceptOffer(offerId)
        );
    }

    @PutMapping("/{offerId}/reject")
    public ResponseEntity<OfferResponse> rejectOffer(
            @PathVariable UUID offerId) {

        return ResponseEntity.ok(
                offerService.rejectOffer(offerId)
        );
    }

    @PutMapping("/{offerId}/complete")
    public ResponseEntity<OfferResponse> completeOffer(
            @PathVariable UUID offerId) {

        return ResponseEntity.ok(
                offerService.completeOffer(offerId)
        );
    }
}