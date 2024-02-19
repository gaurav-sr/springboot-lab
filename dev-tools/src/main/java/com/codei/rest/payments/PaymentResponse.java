package com.codei.rest.payments;

/**
 * Created by gauravsrivastava on 15/11/2016.
 */
public class PaymentResponse {

    private String id;
    private String message;

    public PaymentResponse() {

    }

    public PaymentResponse(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
