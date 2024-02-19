package com.codei.springboot.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void test() {
        paymentService.addCard("101", 12345233L);
        paymentService.addCard("102", 12345234L);
        Assert.assertEquals(2, paymentService.getCardCount().longValue());
    }
}
