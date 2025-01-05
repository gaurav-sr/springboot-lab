package com.codeigen.rest.payments;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {



    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = null;

        if(validateCardId(paymentRequest.getCardId())) {
            paymentResponse = PaymentResponse.builder().message(Constants.SUCCESS_MESSAGE).cardId(getUniqueId()).build();
        } else {
            paymentResponse = new PaymentResponse(getUniqueId(), Constants.FAIL_MESSAGE);
        }
        return paymentResponse;
    }

    @Override
    public PaymentResponse processPayment() {
        return new PaymentResponse(null,null);
    }

    private boolean validateCardId(String cardId) {
        return cardId.length() == Constants.CARD_ID_LENGTH;
    }

    private String getUniqueId() {
        return UUID.randomUUID().toString();
    }
}
