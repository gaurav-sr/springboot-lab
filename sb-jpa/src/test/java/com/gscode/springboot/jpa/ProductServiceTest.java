package com.gscode.springboot.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void givenTwoProductsAreAdded_whenCountIsCallded_ThenTwoCountIsReturned() {
        productService.addProduct(101L, "iPhone");
        productService.addProduct(102L, "iPad");
        Assertions.assertEquals(2, productService.getProductCount().longValue());
    }
}