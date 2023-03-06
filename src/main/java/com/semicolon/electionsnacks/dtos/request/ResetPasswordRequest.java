package com.semicolon.electionsnacks.dtos.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String emailAddress;
    private String password;
    private String token;
}
