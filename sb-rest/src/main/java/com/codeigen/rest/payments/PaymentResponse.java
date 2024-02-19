package com.codeigen.rest.payments;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String id;
    private String message;
}
