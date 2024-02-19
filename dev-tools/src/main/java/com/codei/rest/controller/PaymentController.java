package com.codei.rest.controller;

import com.codei.rest.payments.PaymentRequest;
import com.codei.rest.payments.PaymentResponse;
import com.codei.rest.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private PaymentService paymentService;


    @RequestMapping(path = "/",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> info() {
        System.out.println("Received....");
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setMessage("SUCCESS");
        paymentResponse.setId("1");
        return new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.OK);
    }

    /**
     * This method handles payment. The default media type is JSON
     *
     * @param paymentRequest PaymentRequest object
     * @return paymentResponse
     */
    @RequestMapping(path="/payments", method= RequestMethod.POST)
    public ResponseEntity<PaymentResponse> handlePayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);
        return new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.OK);
    }

    @RequestMapping(path="/emptyResponse", method = RequestMethod.GET)
    public PaymentResponse getDummyResponse() {
        return paymentService.processPayment();
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
