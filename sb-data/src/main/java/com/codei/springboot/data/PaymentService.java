package com.codei.springboot.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    public void addCard(String id, Long cardNumber) {
        PaymentCard paymentCard = new PaymentCard();
        paymentCard.setId(id);
        paymentCard.setCardNumber(cardNumber);
        paymentRepository.save(paymentCard);
    }

    @Transactional
    public Long getCardCount() {
        return paymentRepository.count();
    }

}
