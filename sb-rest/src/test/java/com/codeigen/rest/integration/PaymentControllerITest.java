package com.codeigen.rest.integration;

import com.codeigen.rest.payments.Constants;
import com.codeigen.rest.payments.PaymentRequest;
import com.codeigen.rest.payments.PaymentResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This is integration test using Spring's TestRestTemplate
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("itest")
public class PaymentControllerITest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testHello() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(Constants.HELLO_MESSAGE, responseEntity.getBody());
    }

    @Test
    public void testPayments() {
        PaymentRequest paymentRequest = PaymentRequest.builder().cardId("3124243123456789").build();
        ResponseEntity<PaymentResponse> response = testRestTemplate.postForEntity("/payment", paymentRequest, PaymentResponse.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        PaymentResponse paymentResponse = response.getBody();
        assert paymentResponse != null;
        Assert.assertEquals(paymentResponse.getMessage(), Constants.SUCCESS_MESSAGE);
    }

}
