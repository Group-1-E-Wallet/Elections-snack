package com.semicolon.electionsnacks.services.registration;

import com.semicolon.electionsnacks.dtos.request.RegistrationRequest;
import com.semicolon.electionsnacks.dtos.request.ResendTokenRequest;
import com.semicolon.electionsnacks.dtos.request.TokenConfirmationRequest;
import com.semicolon.electionsnacks.dtos.response.SignUpResponse;
import com.semicolon.electionsnacks.exceptions.RegistrationException;
import com.semicolon.electionsnacks.models.Token;
import com.semicolon.electionsnacks.models.Voter;
import com.semicolon.electionsnacks.services.email.EmailSender;
import com.semicolon.electionsnacks.services.token.TokenService;
import com.semicolon.electionsnacks.services.voters.VoterService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private TokenService tokenService;
    private EmailSender emailSender;
    private VoterService voterService;
    @Override
    public String verifyToken(TokenConfirmationRequest tokenConfirmationRequest) {
        Token token = tokenService.getConfirmationToken(
                        tokenConfirmationRequest.getToken()).
                orElseThrow(() ->
                        new RegistrationException("Invalid token"));
        if (token.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Token has expired");
        tokenService.setConfirmationToken(token.getToken());
        voterService.enableVoter(tokenConfirmationRequest.getEmailAddress());
        return "Confirmed!";
    }

    @Override
    public String resendToken (ResendTokenRequest resendTokenRequest){
        Voter foundVoter = voterService.findByEmailAddressIgnoreCase(resendTokenRequest.getEmailAddress()).orElseThrow(() -> new
                IllegalStateException("this email does not exist"));
        System.out.println(foundVoter.isVerified());
        if (foundVoter.isVerified()) throw new RegistrationException("Already verified");
        else {
            String token = generateToken(foundVoter);
            emailSender.send(resendTokenRequest.getEmailAddress(), buildEmail(foundVoter.getFirstName(), token));
        }
        return "token has been resent successfully";
    }

    private String buildEmail(String firstName, String token) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + firstName + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + token + "</p></blockquote>\n Link will expire in 10 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
    @Override
    public String generateToken(Voter foundVoter) {
        SecureRandom random = new SecureRandom();

        String token = String.valueOf(1000 + random.nextInt(9999));
        Token confirmationToken = new Token(token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                foundVoter
        );
        tokenService.saveConfirmationToken(confirmationToken);
        return confirmationToken.getToken();
    }
    @Override
    public SignUpResponse registration(RegistrationRequest registrationRequest)  throws MessagingException {
            boolean emailExists = voterService
                    .findByEmailAddressIgnoreCase(registrationRequest.getEmailAddress())
                    .isPresent();
            if (emailExists)throw new IllegalStateException("Email Address already exists");

            Voter user = new Voter(
                    registrationRequest.getEmailAddress(),
                    registrationRequest.getFirstName(),
                    registrationRequest.getLastName(),
                    hashPassword(registrationRequest.getPassword())
            );

            voterService.getUser(user);
            String token = generateToken(user);
            emailSender.send(registrationRequest.getEmailAddress(), buildEmail(registrationRequest.getFirstName(), token));

            SignUpResponse sign = new SignUpResponse();
            sign.setEmailAddress(registrationRequest.getEmailAddress());
            sign.setFirstName(registrationRequest.getFirstName());
            sign.setLastName(registrationRequest.getLastName());
            return sign;
    }

    private String hashPassword (String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


}


