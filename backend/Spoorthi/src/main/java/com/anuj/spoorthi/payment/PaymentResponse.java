package com.anuj.spoorthi.payment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {

    private String name;

    private double amountDonated;

    private int phoneNumber;

    private PaymentMode paymentMode;

    private String emailId;

    private int transactionId;

    private LocalDateTime dateTime;
}
