package com.anuj.spoorthi.payment;

import java.util.List;

public interface PaymentService {
    String addPayment(PaymentRequest paymentRequest);

    List<PaymentResponse> getAllPayments();

}
