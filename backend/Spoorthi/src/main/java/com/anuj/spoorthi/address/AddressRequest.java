package com.anuj.spoorthi.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressRequest {

    private String streetName;


    private String locality;

    @Size(min = 6, max = 6)
    private int pincode;

    @NotBlank
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String state;

    @NotBlank
    private String country;
}
