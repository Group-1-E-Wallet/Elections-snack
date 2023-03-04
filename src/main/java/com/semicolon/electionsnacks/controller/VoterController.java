package com.semicolon.electionsnacks.controller;

import com.semicolon.electionsnacks.dtos.request.EmailAddressRequest;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;
import com.semicolon.electionsnacks.dtos.response.ApiErrorResponse;
import com.semicolon.electionsnacks.dtos.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.semicolon.electionsnacks.services.voters.VoterService;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class VoterController {
    @Autowired
    private VoterService voterService;
    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }
    @PostMapping("/forgot/password")
    public ResponseEntity<?>forgetPassword(@Valid @RequestBody EmailAddressRequest emailAddressRequest,
                                           HttpServletRequest httpServletRequest){
        String response = voterService.forgotPassword(emailAddressRequest);
        ApiErrorResponse apiResponse = ApiErrorResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(response)
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK)
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/change/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest,
                                            HttpServletRequest httpServletRequest){
        String change = voterService.changePassword(updatePasswordRequest);
        ApiErrorResponse apiResponse = ApiErrorResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(change)
                .statusCode(HttpStatus.OK)
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
