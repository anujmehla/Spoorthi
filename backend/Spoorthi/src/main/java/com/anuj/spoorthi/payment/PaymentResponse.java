package com.anuj.spoorthi.payment;

import java.time.LocalDateTime;

public record PaymentResponse(
        String name,
        double amountDonated,
        long phoneNumber,
        PaymentMode paymentMode,
        String emailId,
        long transactionId,
        LocalDateTime dateTime
) {}
