package com.semicolon.electionsnacks.services.voters;

import com.semicolon.electionsnacks.exceptions.RegistrationException;
import com.semicolon.electionsnacks.models.Voter;
import com.semicolon.electionsnacks.repositories.VoterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoterServiceImpl implements VoterService{
    private VoterRepository voterRepository;

    @Override
    public void enableVoter(String email) {
        Voter foundEmail = voterRepository.
                findByEmailAddressIgnoreCase(email).
                orElseThrow(()->
                        new RegistrationException("Invalid email"));
        foundEmail.setIsVerified(true);
    }

    @Override
    public Optional<Voter> findByEmailAddressIgnoreCase(String emailAddress) {
        return Optional.of(voterRepository.findByEmailAddressIgnoreCase(emailAddress).orElseThrow());
    }
}
