package com.semicolon.electionsnacks.dtos.requests;

import lombok.Data;

@Data
public class ResendTokenRequest {
    private String emailAddress;
}
