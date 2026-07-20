package com.omnitrade.offer_service.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class KafkaTopics {

    // Offer Events
    public static final String OFFER_CREATED = "offer.created";
    public static final String OFFER_UPDATED = "offer.updated";
    public static final String OFFER_CANCELLED = "offer.cancelled";
    public static final String OFFER_ACCEPTED = "offer.accepted";
    public static final String OFFER_REJECTED = "offer.rejected";
    public static final String OFFER_COMPLETED = "offer.completed";
    public static final String OFFER_EXPIRED = "offer.expired";

    // Counter Offers
    public static final String COUNTER_OFFER_CREATED = "offer.counter.created";

}