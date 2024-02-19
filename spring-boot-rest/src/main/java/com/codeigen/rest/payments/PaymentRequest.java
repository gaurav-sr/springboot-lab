package com.codeigen.rest.payments;

/**
 * Created by gauravsrivastava on 15/11/2016.
 */
public class PaymentRequest {
    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof  PaymentRequest)) {
            return false;
        }
        PaymentRequest pr = (PaymentRequest)object;
        if(pr.getCardId().equals(this.cardId)) {
            return true;
        }
        return false;
    }
}
