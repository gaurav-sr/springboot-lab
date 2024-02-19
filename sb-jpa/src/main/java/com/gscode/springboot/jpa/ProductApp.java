package com.gscode.springboot.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProductApp {

    public static void main( String[] args ) {
        ApplicationContext ctx = SpringApplication.run(ProductApp.class, args);
        ProductRepository productRepository = (ProductRepository)ctx.getBean("productRepository");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("MacBook Pro");
        productRepository.save(productEntity);

        Iterable<ProductEntity> productEntityIterator = productRepository.findAll();
        for(ProductEntity productEntityX : productEntityIterator) {
            System.out.println(productEntityX.getId());
            System.out.println(productEntityX.getName());
        }
    }
}
