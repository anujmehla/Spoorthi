package com.anuj.Spoorthi.programs;

import com.anuj.Spoorthi.Address.AddressRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;


@Data
public class ProgramRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private  LocalDate endDate;

    private String extra;

    @NotNull
    private double goalAmount;

    @NotNull
    private double raisedAmount;

    @NotNull
    private AddressRequest address;


    private boolean isDeleted;

}
