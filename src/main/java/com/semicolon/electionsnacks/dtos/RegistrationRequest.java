package com.semicolon.electionsnacks.dtos;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String emailAddress;
    private String password;

}
