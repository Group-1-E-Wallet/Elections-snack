package com.semicolon.electionsnacks.dtos.request;

import lombok.Data;

@Data
public class ResendTokenRequest {
    private String emailAddress;
}
