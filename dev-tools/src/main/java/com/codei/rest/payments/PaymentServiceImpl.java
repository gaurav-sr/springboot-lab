package com.codei.rest.payments;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {



    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(getUniqueId());
        if(validateCardId(paymentRequest.getCardId())) {
            paymentResponse.setMessage(Constants.SUCCESS_MESSAGE);
        } else {
            paymentResponse.setMessage(Constants.FAIL_MESSAGE);
        }
        return paymentResponse;
    }

    @Override
    public PaymentResponse processPayment() {
        return new PaymentResponse();
    }

    private boolean validateCardId(String cardId) {
        return cardId.length() == Constants.CARD_ID_LENGTH;
    }

    private String getUniqueId() {
        return UUID.randomUUID().toString();
    }
}
