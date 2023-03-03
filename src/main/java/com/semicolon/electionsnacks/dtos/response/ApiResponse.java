package com.semicolon.electionsnacks.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
@Builder
@AllArgsConstructor
public class ApiResponse {
    private ZonedDateTime timeStamp;
    private boolean isSuccessful;
    private Object data;
    private int status;
    private String path;
}
