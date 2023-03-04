package com.semicolon.electionsnacks.dtos.requests;

import lombok.Data;

@Data
public class TokenConfirmationRequest {
    private String token;
    private String email;
}
