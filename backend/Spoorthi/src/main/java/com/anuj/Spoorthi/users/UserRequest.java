package com.anuj.Spoorthi.users;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @Size(min = 3)
    @NotBlank(message = "First name can't be null")
    private String firstName;

    @NotBlank(message = "Last name can't be null")
    private String lastName;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Email
    private String email;

    @NotBlank
    @Size(min = 10,max = 10)
    private String phone;

    private Gender gender;

    private Role role;

}
