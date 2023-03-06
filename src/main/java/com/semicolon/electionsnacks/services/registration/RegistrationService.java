package com.semicolon.electionsnacks.services.registration;

import com.semicolon.electionsnacks.dtos.request.RegistrationRequest;
import com.semicolon.electionsnacks.dtos.request.ResendTokenRequest;
import com.semicolon.electionsnacks.dtos.request.TokenConfirmationRequest;
import com.semicolon.electionsnacks.dtos.response.SignUpResponse;
import com.semicolon.electionsnacks.models.Voter;
import jakarta.mail.MessagingException;

public interface RegistrationService {
    String verifyToken(TokenConfirmationRequest tokenConfirmationRequest);
    String resendToken(ResendTokenRequest resendTokenRequest);
    String generateToken(Voter foundVoter);
    SignUpResponse registration(RegistrationRequest registrationRequest) throws MessagingException;

}
