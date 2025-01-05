package com.codeigen.rest.payments;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private String cardId;
    private String message;
}
