package com.omnitrade.offer_service.repository;

import com.omnitrade.offer_service.model.entity.OfferItem;
import com.omnitrade.offer_service.model.entity.OfferStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfferStatusHistoryRepository extends JpaRepository<OfferStatusHistory, UUID> {
}
