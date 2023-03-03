package com.semicolon.electionsnacks.exceptions;

<<<<<<< HEAD

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler
    public ResponseEntity<?> userAlreadyExistException(com.semicolon.electionsnacks.exceptions.RegistrationException registrationException,
                                                       HttpServletRequest httpServletRequest){
        com.semicolon.electionsnacks.exceptions.ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .path(httpServletRequest.getRequestURI())
                .data(registrationException.getMessage())
                .build();

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler
    public ResponseEntity<?> accountException(com.semicolon.electionsnacks.exceptions.AccountException accountException,
                                              HttpServletRequest httpServletRequest){
        ApiResponse apiResponse =  ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .path(httpServletRequest.getRequestURI())
                .data(accountException.getMessage())
                .build();

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler
    public  ResponseEntity<ApiResponse>GenericHandler(Exception exception, HttpServletRequest httpServletRequest){
        com.semicolon.electionsnacks.exceptions.ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(exception.getMessage())
                .path(httpServletRequest.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
=======
import com.semicolon.electionsnacks.dtos.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> handleRegistrationException(GenericException exception, HttpServletRequest request){
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .isSuccessful(false)
                .data(exception.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .path(request.getRequestURI())
                .timeStamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
>>>>>>> in-dev
    }
}
