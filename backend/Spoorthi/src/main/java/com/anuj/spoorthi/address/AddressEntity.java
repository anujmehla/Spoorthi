package com.anuj.spoorthi.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String streetName;


    private String locality;

    @Digits(integer = 6, fraction = 0)
    private Integer pincode;

    @NotBlank
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

//    @OneToOne(mappedBy = "address")
//    private ProgramEntity program;
}