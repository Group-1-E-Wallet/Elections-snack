package com.semicolon.electionsnacks.controller;

import com.semicolon.electionsnacks.dtos.request.RegistrationRequest;
import com.semicolon.electionsnacks.dtos.request.ResendTokenRequest;
import com.semicolon.electionsnacks.dtos.request.TokenConfirmationRequest;
import com.semicolon.electionsnacks.dtos.response.ApiResponse;
import com.semicolon.electionsnacks.services.registration.RegistrationService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/register")
//@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/signUp")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest registrationRequest, HttpServletRequest httpServletRequest) throws MessagingException {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(registrationService.registration(registrationRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
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
