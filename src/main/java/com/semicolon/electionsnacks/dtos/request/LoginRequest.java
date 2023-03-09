package com.semicolon.electionsnacks.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest{
    @Email(message="Please a valid email")
    private String emailAddress;
   // @Pattern(regexp="^([a-zA-Z\\d@*#$&!]{5,15})$")
    private String password;
}
