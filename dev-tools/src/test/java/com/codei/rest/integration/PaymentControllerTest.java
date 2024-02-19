package com.codei.rest.integration;

import com.codei.rest.payments.Constants;
import com.codei.rest.payments.PaymentRequest;
import com.codei.rest.payments.PaymentResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This is integration test using Spring's TestRestTemplate
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PaymentControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testHello() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(Constants.HELLO_GREETING, responseEntity.getBody());
    }

    @Test
    public void testPayments() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCardId("3124243123456789");
        ResponseEntity<PaymentResponse> response = testRestTemplate.postForEntity("/payments", paymentRequest, PaymentResponse.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        PaymentResponse paymentResponse = response.getBody();
        Assert.assertEquals(paymentResponse.getMessage(), Constants.SUCCESS_MESSAGE);
    }

}
