package com.anuj.spoorthi.programs;

import com.anuj.spoorthi.address.AddressResponse;
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

    private AddressResponse address;
}
