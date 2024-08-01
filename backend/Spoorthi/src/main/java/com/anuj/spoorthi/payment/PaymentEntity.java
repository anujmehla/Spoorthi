package com.anuj.spoorthi.payment;


import com.anuj.spoorthi.shared.entity.BaseEntity;
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
public class PaymentEntity  extends BaseEntity {

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
