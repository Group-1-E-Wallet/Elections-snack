package com.semicolon.electionsnacks.services.token;

import com.semicolon.electionsnacks.models.Token;
import com.semicolon.electionsnacks.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Optional<Token> getConfirmationToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void setConfirmationToken(String token) {
        tokenRepository.confirmedAt(LocalDateTime.now(), token);
    }

    @Override
    public void saveConfirmationToken(Token confirmationToken) {
        tokenRepository.save(confirmationToken);
    }
}
