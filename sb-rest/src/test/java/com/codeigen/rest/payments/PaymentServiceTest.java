package com.codeigen.rest.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentServiceTest {

    @Test
    public void test() {
        PaymentService paymentService = new PaymentServiceImpl();
        PaymentRequest paymentRequest = PaymentRequest.builder().cardId("2134411").build();
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);
        Assertions.assertNotNull(paymentResponse);
        Assertions.assertEquals("FAIL", paymentResponse.getMessage());
    }

}
