package com.anuj.spoorthi.payment;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    private double amountDonated;

    @Size(min = 10, max = 100)
    private int phoneNumber;

//    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Email
    private String emailId;

    @NotBlank
    private Integer transactionId;

    @NotBlank
    private LocalDateTime dateTime;
}
