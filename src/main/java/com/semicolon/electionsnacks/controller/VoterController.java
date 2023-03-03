package com.semicolon.electionsnacks.controller;

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

import java.time.ZonedDateTime;

@RestController
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
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
