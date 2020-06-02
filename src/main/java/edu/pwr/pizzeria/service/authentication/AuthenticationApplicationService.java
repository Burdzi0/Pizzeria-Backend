package edu.pwr.pizzeria.service.authentication;

import edu.pwr.pizzeria.model.authentication.CredentialsDto;
import edu.pwr.pizzeria.model.authentication.EmailDto;
import edu.pwr.pizzeria.model.authentication.TokenDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class AuthenticationApplicationService {

    private VerificationService verificationService;
    private AuthenticationService authenticationService;

    public AuthenticationApplicationService(VerificationService verificationService, AuthenticationService authenticationService) {
        this.verificationService = verificationService;
        this.authenticationService = authenticationService;
    }

    @Transactional
    public TokenDto login(CredentialsDto credentialsDto) {
        return authenticationService.login(credentialsDto);
    }

    @Transactional
    public void register(CredentialsDto credentialsDto, HttpServletRequest request) {
        authenticationService.register(credentialsDto, request.getServerName(), request.getServerPort());
    }

    @Transactional
    public void confirmRegistration(UUID uuid) {
        verificationService.confirmRegistration(uuid);
    }

    public void resetPassword(EmailDto emailDto) {
        authenticationService.reset(emailDto);
    }
}
