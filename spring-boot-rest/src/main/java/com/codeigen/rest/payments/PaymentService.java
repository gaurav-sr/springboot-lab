package com.codeigen.rest.payments;

public interface PaymentService {

    PaymentResponse processPayment(PaymentRequest paymentRequest);

    PaymentResponse processPayment();

}
