package com.codeigen.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication encompasses @Configuration, @EnableAutoConfiguration and @ComponentScan
 */
@SpringBootApplication
public class PaymentApp {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApp.class, args);
    }

}
