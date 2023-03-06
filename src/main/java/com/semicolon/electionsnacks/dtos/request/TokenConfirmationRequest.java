package com.semicolon.electionsnacks.dtos.request;

import lombok.Data;

@Data
public class TokenConfirmationRequest {
    private String token;
    private String emailAddress;
}
