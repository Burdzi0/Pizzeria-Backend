package edu.pwr.pizzeria.service.authentication;

import edu.pwr.pizzeria.exception.EmailAlreadyRegisteredException;
import edu.pwr.pizzeria.exception.InvalidLoginCredentialsException;
import edu.pwr.pizzeria.model.authentication.CredentialsDto;
import edu.pwr.pizzeria.model.authentication.EmailDto;
import edu.pwr.pizzeria.model.authentication.TokenDto;
import edu.pwr.pizzeria.model.user.CustomerUser;
import edu.pwr.pizzeria.repository.CustomerUserRepository;
import edu.pwr.pizzeria.security.JwtUtil;
import edu.pwr.pizzeria.service.MailApplicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static edu.pwr.pizzeria.model.user.Role.ROLE_USER;

@Service
public class AuthenticationService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;
    private CustomerUserRepository customerUserRepository;
    private PasswordEncoder passwordEncoder;
    private MailApplicationService mailApplicationService;
    private VerificationService verificationService;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserDetailsService userDetailsService,
                                 JwtUtil jwtUtil,
                                 CustomerUserRepository customerUserRepository,
                                 PasswordEncoder passwordEncoder,
                                 MailApplicationService mailApplicationService,
                                 VerificationService verificationService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.customerUserRepository = customerUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailApplicationService = mailApplicationService;
        this.verificationService = verificationService;
    }

    public TokenDto login(CredentialsDto credentialsDto) {
        authenticate(credentialsDto);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(credentialsDto.getMail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new TokenDto(jwt);
    }

    private void authenticate(CredentialsDto credentialsDto) {
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken(credentialsDto));
        } catch (BadCredentialsException | DisabledException e) {
            logger.info("Bad credentials");
            throw new InvalidLoginCredentialsException("Invalid credentials");
        }
    }

    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(CredentialsDto credentialsDto) {
        return new UsernamePasswordAuthenticationToken(credentialsDto.getMail(), credentialsDto.getPassword());
    }

    public void reset(EmailDto emailDto) {
        mailApplicationService.sendPasswordResettingMail(emailDto.getMail());
    }

    public void register(CredentialsDto credentialsDto, String serverName, int serverPort) {
        customerUserRepository.getCustomerUserByMail(credentialsDto.getMail())
                .ifPresent(customer -> {
                    logger.info("Not registering because account with given mail exists");
                    throw new EmailAlreadyRegisteredException();
                });

        customerUserRepository.save(newCustomerUser(credentialsDto));
        logger.info("Created new user");

        final var verificationId = verificationService.createNewVerification(credentialsDto.getMail());
        mailApplicationService.sendConfirmRegisterMail(credentialsDto.getMail(), newVerificationLink(serverName, serverPort, verificationId));
    }

    private CustomerUser newCustomerUser(CredentialsDto credentialsDto) {
        final String hashedPassword = passwordEncoder.encode(credentialsDto.getPassword());
        return new CustomerUser(credentialsDto.getMail(), hashedPassword, ROLE_USER);
    }

    private String newVerificationLink(String serverName, int serverPort, UUID verificationId) {
        return "https://" + serverName + ":" + serverPort + "/v1/register/" + verificationId;
    }

    public void registerAdmin(CredentialsDto credentialsDto) {
        customerUserRepository.getCustomerUserByMail(credentialsDto.getMail())
                .ifPresent(customer -> {
                    logger.info("Not registering because account with given mail exists");
                    throw new EmailAlreadyRegisteredException();
                });

        customerUserRepository.save(newCustomerUser(credentialsDto));
        logger.info("Created new user");

        final var verificationId = verificationService.createNewVerification(credentialsDto.getMail());
        verificationService.confirmRegistration(verificationId);
    }

}
