package com.codeigen.rest.integration;

import com.codeigen.rest.payments.Constants;
import com.codeigen.rest.payments.PaymentRequest;
import com.codeigen.rest.payments.PaymentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.codeigen.rest.payments.Constants.*;

/**
 * This is integration test using Spring's TestRestTemplate
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("itest")
public class PaymentControllerITest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testHello() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/", String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(HELLO_MESSAGE, responseEntity.getBody());
    }

    @Test
    public void testPayments() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCardId("3124243123456789");
        ResponseEntity<PaymentResponse> response = testRestTemplate.postForEntity("/payment", paymentRequest, PaymentResponse.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        PaymentResponse paymentResponse = response.getBody();
        assert paymentResponse != null;
        Assertions.assertEquals(paymentResponse.getMessage(), Constants.SUCCESS_MESSAGE);
    }

}
