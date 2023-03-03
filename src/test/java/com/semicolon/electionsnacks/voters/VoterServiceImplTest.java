package com.semicolon.electionsnacks.voters;

import com.semicolon.electionsnacks.dtos.request.EmailAddressRequest;
import com.semicolon.electionsnacks.dtos.request.UpdatePasswordRequest;
import com.semicolon.electionsnacks.services.voters.VoterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VoterServiceImplTest {

    @Autowired
    VoterService voterService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testThatUserCanChangePassword() {
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setEmailAddress("kennie@gmail.com");
        updatePasswordRequest.setOldPassword("kehinde@113");
        updatePasswordRequest.setNewPassword("semicolon$278");
        var response = voterService.changePassword(updatePasswordRequest);
        assertEquals("Your password has been successfully changed", response);
    }

    @Test
    void testThatForgotPasswordMethodWorks() {
        EmailAddressRequest emailAddressRequest = new EmailAddressRequest();
        emailAddressRequest.setEmailAddress("rooneydavies@gmail.com");
        var result = voterService.forgotPassword(emailAddressRequest);
        assertEquals("We have sent a new password link to your email. Please check.", result);
    }
}