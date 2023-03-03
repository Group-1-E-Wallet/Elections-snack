package com.semicolon.electionsnacks.controller;

import com.semicolon.electionsnacks.dtos.requests.ResendTokenRequest;
import com.semicolon.electionsnacks.dtos.requests.TokenConfirmationRequest;
import com.semicolon.electionsnacks.dtos.response.ApiResponse;
import com.semicolon.electionsnacks.services.registration.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping("/resend-token")
    public ResponseEntity<?> resendToken(
            @RequestBody ResendTokenRequest resendTokenRequest,
            HttpServletRequest httpServletRequest) {
        ApiResponse apiResponse=ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(registrationService.resendToken(resendTokenRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/verify-token")
    public ResponseEntity<?> verifyToken(
            @RequestBody
            TokenConfirmationRequest tokenConfirmationRequest,
            HttpServletRequest httpServletRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(registrationService.verifyToken(tokenConfirmationRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
