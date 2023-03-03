package com.semicolon.electionsnacks.controller;

<<<<<<< HEAD
import com.semicolon.electionsnacks.dtos.RegistrationRequest;
import com.semicolon.electionsnacks.exceptions.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.semicolon.electionsnacks.voters.VoterService;
=======
import com.semicolon.electionsnacks.dtos.request.EmailAddressRequest;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;
import com.semicolon.electionsnacks.dtos.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.semicolon.electionsnacks.services.voters.VoterService;
>>>>>>> in-dev

import java.time.ZonedDateTime;

@RestController
<<<<<<< HEAD
@RequestMapping("api/v1/voter")
public class VoterController {
    @Autowired
    VoterService voterService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest registrationRequest, HttpServletRequest httpServletRequest){
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(voterService.registration(registrationRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
=======
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
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(response)
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK)
>>>>>>> in-dev
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

<<<<<<< HEAD

=======
    @PostMapping("/change/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest,
                                            HttpServletRequest httpServletRequest){
        String change = voterService.changePassword(updatePasswordRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(change)
                .statusCode(HttpStatus.OK)
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
>>>>>>> in-dev
}
