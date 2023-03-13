package com.semicolon.electionsnacks.controller;
import com.semicolon.electionsnacks.dtos.UpdateRegistration.UpdateRegistrationRequest;
import com.semicolon.electionsnacks.dtos.request.ForgotPasswordRequest;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;
import com.semicolon.electionsnacks.dtos.response.ApiResponse;
import com.semicolon.electionsnacks.services.UpdateRegistration.UpdateRegistrationService;
import com.semicolon.electionsnacks.services.voters.VoterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZonedDateTime;


@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class VoterController {
    @Autowired
    private VoterService voterService;

    @Autowired
    private UpdateRegistrationService updateRegistrationService;

    @PostMapping("/forgot/password")
    public ResponseEntity<?>forgetPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest,
                                           HttpServletRequest httpServletRequest){
            ApiResponse apiResponse = ApiResponse.builder()
             .status(HttpStatus.OK.value())
                    .data(voterService.forgotPassword(forgotPasswordRequest))
                    .timeStamp(ZonedDateTime.now())
                    .path(httpServletRequest.getRequestURI())
                    .isSuccessful(true)
                    .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PostMapping("/change/password")
    public ResponseEntity<?> changePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest,
                                            HttpServletRequest httpServletRequest){
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(voterService.changePassword(updatePasswordRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping("/updateVotersRegistration")
    public ResponseEntity<?> updateRegistration(@RequestBody UpdateRegistrationRequest updateRegistrationRequest, HttpServletRequest httpServletRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(updateRegistrationService.updateRegister(updateRegistrationRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
