CREATE TABLE offers (

                        id CHAR(36) PRIMARY KEY,

                        item_id CHAR(36) NOT NULL,

                        seller_id CHAR(36) NOT NULL,

                        buyer_id CHAR(36) NOT NULL,

                        offer_type VARCHAR(30) NOT NULL,

                        cash_amount DECIMAL(12,2) NOT NULL DEFAULT 0,

                        message VARCHAR(1000),

                        status VARCHAR(30) NOT NULL,

                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                            ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_offer_item
    ON offers(item_id);

CREATE INDEX idx_offer_seller
    ON offers(seller_id);

CREATE INDEX idx_offer_buyer
    ON offers(buyer_id);

CREATE INDEX idx_offer_status
    ON offers(status);