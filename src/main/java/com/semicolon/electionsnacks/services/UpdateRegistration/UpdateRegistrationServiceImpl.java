package com.semicolon.electionsnacks.services.UpdateRegistration;

import com.semicolon.electionsnacks.dtos.UpdateRegistration.UpdateRegistrationRequest;
import com.semicolon.electionsnacks.models.Voter;
import com.semicolon.electionsnacks.repositories.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UpdateRegistrationServiceImpl  implements UpdateRegistrationService{

   @Autowired
    VoterRepository voterRepository;

    public Object updateRegister(UpdateRegistrationRequest updateRegistrationRequest) {
        Optional<Voter> foundVoter = voterRepository.findByEmailAddressIgnoreCase(updateRegistrationRequest.getEmail());
        Voter voter = foundVoter.get();
        voter.setFirstName(updateRegistrationRequest.getFirstName());
        voter.setLastName(updateRegistrationRequest.getLastName());
        voter.setGender(updateRegistrationRequest.getGender());
        voter.setMaritalStatus(updateRegistrationRequest.getMaritalStatus());
        voter.setLgaOfOrigin(updateRegistrationRequest.getLgaOfOrigin());
        voter.setState(updateRegistrationRequest.getState());
        voter.setResidentialAddress(updateRegistrationRequest.getResidentialAddress());
        voter.setDateOfBirth(updateRegistrationRequest.getDateOfBirth());
        return null;
    }
}
