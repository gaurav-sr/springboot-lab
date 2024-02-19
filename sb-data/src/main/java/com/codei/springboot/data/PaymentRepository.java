package com.codei.springboot.data;

import org.springframework.data.repository.CrudRepository;


public interface PaymentRepository extends CrudRepository<PaymentCard, String> {
}
