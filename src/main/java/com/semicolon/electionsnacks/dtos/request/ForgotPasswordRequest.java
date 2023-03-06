package com.semicolon.electionsnacks.dtos.request;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
public class ForgotPasswordRequest {
    @Email(message = "This field cannot be blank")
    private String emailAddress;
}

