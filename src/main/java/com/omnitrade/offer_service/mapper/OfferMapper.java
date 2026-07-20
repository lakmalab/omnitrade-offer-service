package com.omnitrade.offer_service.mapper;

import com.omnitrade.offer_service.kafka.event.*;
import com.omnitrade.offer_service.model.dto.response.OfferResponse;
import com.omnitrade.offer_service.model.entity.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferResponse toResponse(Offer offer);

    OfferCreatedEvent toCreatedEvent(Offer offer);

    OfferUpdatedEvent toUpdatedEvent(Offer offer);

    OfferAcceptedEvent toAcceptedEvent(Offer offer);

    OfferRejectedEvent toRejectedEvent(Offer offer);

    OfferCancelledEvent toCancelledEvent(Offer offer);

    OfferCompletedEvent toCompletedEvent(Offer offer);

}