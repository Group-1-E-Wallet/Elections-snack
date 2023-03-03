package com.semicolon.electionsnacks.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {
    @Email(message = "This field must not be empty")
    private String emailAddress;
    @Pattern(regexp="^([a-zA-Z\\d@*#$&!]{8,15})$")
    private String oldPassword;
    @Pattern(regexp="^([a-zA-Z\\d@*#$&!]{8,15})$")
    private String newPassword;
}
