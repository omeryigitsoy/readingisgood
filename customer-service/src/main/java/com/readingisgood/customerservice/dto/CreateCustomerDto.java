package com.readingisgood.customerservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateCustomerDto {

    private Long id;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2,message = "First name length must be greater than 2 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2,message = "Last name length must be greater than 2 characters")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6,max = 10,message = "Password lenghth must be between 6-10 characters")
    private String password;
}
