package com.anuj.Spoorthi.programs;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "program")

public class ProgramEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

    @Column(name = "deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT 'FALSE'")
    private boolean isDeleted;


}