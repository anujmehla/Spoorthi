package com.anuj.spoorthi.payment;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PaymentRequest(
        @NotBlank
        String name,

        @NotNull
        Double amountDonated,

        @NotNull
        Long phoneNumber,

        @NotNull
        PaymentMode paymentMode,

        @NotBlank
        String emailId,

        @NotNull
        Integer transactionId,

        @NotNull
        LocalDateTime dateTime
) {
}

