package com.semicolon.electionsnacks.services.registration;

import com.semicolon.electionsnacks.dtos.requests.ResendTokenRequest;
import com.semicolon.electionsnacks.dtos.requests.TokenConfirmationRequest;

public interface RegistrationService {
    String verifyToken(TokenConfirmationRequest tokenConfirmationRequest);
    String resendToken(ResendTokenRequest resendTokenRequest);
}
