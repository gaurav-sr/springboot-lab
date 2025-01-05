package com.codeigen.rest.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentServiceTest {

    @Test
    public void test() {
        PaymentService paymentService = new PaymentServiceImpl();
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCardId("2134411");
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);
        Assertions.assertNotNull(paymentResponse);
        Assertions.assertEquals("FAIL", paymentResponse.getMessage());
    }

}
