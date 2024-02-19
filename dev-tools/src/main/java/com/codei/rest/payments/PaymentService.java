package com.codei.rest.payments;

public interface PaymentService {

    PaymentResponse processPayment(PaymentRequest paymentRequest);

    PaymentResponse processPayment();

}
