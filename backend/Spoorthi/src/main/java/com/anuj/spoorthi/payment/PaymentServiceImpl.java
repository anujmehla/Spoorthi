package com.anuj.spoorthi.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public String addPayment(PaymentRequest paymentRequest) {
        log.info("addPayment");
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(paymentRequest, paymentEntity);
        try {
            paymentRepository.save(paymentEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return "Payment Added";
    }
}
