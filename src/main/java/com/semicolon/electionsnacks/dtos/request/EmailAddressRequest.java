package com.semicolon.electionsnacks.dtos.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class EmailAddressRequest {
    @Email(message = "This field cannot be blank")
    private String emailAddress;
}
