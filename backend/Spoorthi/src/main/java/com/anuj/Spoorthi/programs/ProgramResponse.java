package com.anuj.Spoorthi.programs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProgramResponse {



    private String name;


    private String description;

    private LocalDate startDate;


    private LocalDate endDate;

    private String extra;


    private double goalAmount;


    private double raisedAmount;


    private boolean isDeleted;
}
