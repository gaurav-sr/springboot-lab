package com.gscode.springboot.jpa;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    @Transactional
    public void addProduct(Long id, String name) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);        
        productEntity.setName(name);
        productRepository.save(productEntity);
    }

    @Transactional
    public Long getProductCount() {
        return productRepository.count();
    }

}
