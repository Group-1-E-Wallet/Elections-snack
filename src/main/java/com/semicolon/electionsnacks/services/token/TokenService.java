package com.semicolon.electionsnacks.services.token;

import com.semicolon.electionsnacks.models.Token;

import java.util.Optional;

public interface TokenService {
    Optional<Token> getConfirmationToken(String token);
    void setConfirmationToken(String token);
    void saveConfirmationToken(Token confirmationToken);
}
