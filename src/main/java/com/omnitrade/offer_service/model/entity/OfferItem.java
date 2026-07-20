package com.omnitrade.offer_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "offer_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID offerId;

    @Column(nullable = false)
    private UUID itemId;
}