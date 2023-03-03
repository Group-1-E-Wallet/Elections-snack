package com.semicolon.electionsnacks.voters;
import com.semicolon.electionsnacks.dtos.RegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import com.semicolon.electionsnacks.models.Voter;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.semicolon.electionsnacks.repositories.VoterRepository;

import java.util.Optional;
@Slf4j
@Service
public class VoterServiceImpl implements VoterService{
    @Autowired
    VoterRepository voterRepository;
    @Override
    public Voter registration(RegistrationRequest registrationRequest) {
        boolean emailExists = voterRepository
                .findByEmailAddressIgnoreCase(registrationRequest.getEmailAddress())
                .isPresent();
        log.info("Email exist is "+emailExists);
        if(emailExists) throw new IllegalStateException("Email Address already exists");

        Voter voter = Voter.builder().
                emailAddress(registrationRequest.getEmailAddress()).
                password(hashPassword(registrationRequest.getPassword()))
                .build();
        voterRepository.save(voter);

        return voter;
    }

    private String hashPassword (String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public Optional<Voter> findByEmailAddress(String emailAddress) {
        return voterRepository.findByEmailAddressIgnoreCase(emailAddress);
    }
}
