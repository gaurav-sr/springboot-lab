package com.codeigen.rest.payments;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PaymentRequest {
    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}

    

