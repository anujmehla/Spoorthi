package com.anuj.spoorthi.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public String addPayment(PaymentRequest paymentRequest) {
        log.info("Adding payment request");
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(paymentRequest, paymentEntity);

        try {
            paymentRepository.save(paymentEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return "Payment Added";
    }

    @Override
    public List<PaymentResponse> getAllPayments() {

        List<PaymentEntity> all = paymentRepository.findAll();
        List<PaymentResponse> responses = new ArrayList<>();

        all.forEach(entity -> {
            PaymentResponse response = new PaymentResponse(
                    entity.getName(),
                    entity.getAmountDonated(),
                    entity.getPhoneNumber(),
                    entity.getPaymentMode(),
                    entity.getEmailId(),
                    entity.getTransactionId(),
                    entity.getDateTime()
            );
            responses.add(response);
        });

        return responses;
    }
}
