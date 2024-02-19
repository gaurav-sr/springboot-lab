package com.codei.rest.unit;

import com.codei.rest.payments.*;
import org.junit.Assert;
import org.junit.Test;

public class PaymentServiceTest {

    @Test
    public void test() {
        PaymentService paymentService = new PaymentServiceImpl();
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCardId("2134411");
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);
        Assert.assertEquals(Constants.FAIL_MESSAGE, paymentResponse.getMessage());
    }

}
