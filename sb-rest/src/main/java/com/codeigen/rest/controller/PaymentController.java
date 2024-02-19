package com.codeigen.rest.controller;

import com.codeigen.rest.payments.Constants;
import com.codeigen.rest.payments.PaymentRequest;
import com.codeigen.rest.payments.PaymentResponse;
import com.codeigen.rest.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private PaymentService paymentService;


    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String info() {
        return Constants.HELLO_MESSAGE;
    }

    /**
     * This method handles payment. The default media type is JSON
     *
     * @param paymentRequest PaymentRequest object
     * @return paymentResponse
     */
    @RequestMapping(path="/payment", method= RequestMethod.POST)
    public ResponseEntity<PaymentResponse> handlePayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
