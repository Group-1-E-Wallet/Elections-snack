package com.semicolon.electionsnacks.dtos.response;

import lombok.Data;

@Data
public class SignUpResponse {
    private String lastName;
    private String firstName;
    private String emailAddress;
}
