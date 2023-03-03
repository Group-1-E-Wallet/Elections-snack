package com.semicolon.electionsnacks.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
public class ApiErrorResponse {
    private ZonedDateTime timeStamp;
    private HttpStatus statusCode;
    private String path;
    private Object data;
    private Boolean isSuccessful;
}
