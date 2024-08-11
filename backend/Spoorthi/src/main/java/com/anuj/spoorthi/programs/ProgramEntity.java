package com.anuj.spoorthi.programs;

import com.anuj.spoorthi.address.AddressEntity;
import com.anuj.spoorthi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "program")

public class ProgramEntity  extends BaseEntity {

    @NotBlank
    private String name;

    private String description;

    @NotNull(message = "Event date is required")
    @FutureOrPresent(message = "Event date must be in the present or future")
    private LocalDate startDate;

    @NotNull(message = "Event date is required")
    @FutureOrPresent(message = "Event date must be in the present or future")
    private LocalDate endDate;

    private String extra;

    @NotNull
    private Double goalAmount;

    @NotNull
    private Double raisedAmount;

    @OneToOne(cascade = CascadeType.ALL , optional = false ) // The relationship is mandatory
    @JoinColumn(name = "address_id" , nullable = false) // Foreign key column is mandatory , not nullable
    private AddressEntity address;

    @Column(name = "deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT 'FALSE'")
    private boolean isDeleted;


}