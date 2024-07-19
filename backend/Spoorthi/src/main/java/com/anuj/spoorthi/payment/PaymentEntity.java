package com.anuj.spoorthi.payment;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private Double amountDonated;

    @NotNull
    private Long phoneNumber;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Email
    private String emailId;

    @NotNull
    private Integer transactionId;

    @NotNull
    private LocalDateTime dateTime;

}
