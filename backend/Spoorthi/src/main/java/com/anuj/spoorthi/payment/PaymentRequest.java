package com.anuj.spoorthi.payment;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private double amountDonated;

    @NotBlank
    private int phoneNumber;

//    @NotBlank
    private PaymentMode paymentMode;

    @NotBlank
    private String emailId;

//    @NotBlank
    private Integer transactionId;

    @NotBlank
    private LocalDateTime dateTime;

}
