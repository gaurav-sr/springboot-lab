package com.codeigen.rest.payments;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private String cardId;

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof  PaymentRequest)) {
            return false;
        }
        PaymentRequest pr = (PaymentRequest)object;
        return pr.getCardId().equals(this.cardId);
    }
}
