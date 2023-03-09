package com.semicolon.electionsnacks.dtos.request;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

}
