CREATE TABLE offer_items (

                             id CHAR(36) PRIMARY KEY,

                             offer_id CHAR(36) NOT NULL,

                             item_id CHAR(36) NOT NULL,

                             CONSTRAINT fk_offer_items_offer
                                 FOREIGN KEY (offer_id)
                                     REFERENCES offers(id)
                                     ON DELETE CASCADE
);

CREATE INDEX idx_offer_items_offer
    ON offer_items(offer_id);

CREATE INDEX idx_offer_items_item
    ON offer_items(item_id);