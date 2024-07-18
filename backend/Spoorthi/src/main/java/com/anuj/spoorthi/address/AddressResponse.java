package com.anuj.spoorthi.address;


import lombok.Data;

@Data
public class AddressResponse {

    private String streetName;

    private String locality;

    private int pincode;

    private String city;

    private String district;

    private String state;

    private String country;
}
