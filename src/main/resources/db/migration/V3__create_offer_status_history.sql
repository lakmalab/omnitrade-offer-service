CREATE TABLE offer_status_history (

                                      id CHAR(36) PRIMARY KEY,

                                      offer_id CHAR(36) NOT NULL,

                                      status VARCHAR(30) NOT NULL,

                                      changed_by CHAR(36) NOT NULL,

                                      changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                      CONSTRAINT fk_offer_history_offer
                                          FOREIGN KEY (offer_id)
                                              REFERENCES offers(id)
                                              ON DELETE CASCADE
);

CREATE INDEX idx_offer_history_offer
    ON offer_status_history(offer_id);