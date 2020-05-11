package edu.pwr.pizzeria.service;

import edu.pwr.pizzeria.exception.EmailAlreadyRegisteredException;
import edu.pwr.pizzeria.exception.InvalidLoginCredentialsException;
import edu.pwr.pizzeria.model.authentication.CredentialsDto;
import edu.pwr.pizzeria.model.authentication.EmailDto;
import edu.pwr.pizzeria.model.authentication.TokenDto;
import edu.pwr.pizzeria.model.user.CustomerUser;
import edu.pwr.pizzeria.repository.CustomerUserRepository;
import edu.pwr.pizzeria.security.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static edu.pwr.pizzeria.model.user.Role.ROLE_USER;

@Service
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;
    private CustomerUserRepository customerUserRepository;
    private PasswordEncoder passwordEncoder;
    private MailApplicationService mailApplicationService;

    private final Logger logger = LogManager.getLogger(this.getClass());

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserDetailsService userDetailsService,
                                 JwtUtil jwtUtil,
                                 CustomerUserRepository customerUserRepository,
                                 PasswordEncoder passwordEncoder,
                                 MailApplicationService mailApplicationService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.customerUserRepository = customerUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailApplicationService = mailApplicationService;
    }

    @Transactional
    public TokenDto login(CredentialsDto credentialsDto) {
        authenticate(credentialsDto);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(credentialsDto.getMail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new TokenDto(jwt);
    }

    private void authenticate(CredentialsDto credentialsDto) {
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken(credentialsDto));
        } catch (BadCredentialsException e) {
            logger.info("Bad credentials");
            throw new InvalidLoginCredentialsException("Invalid credentials");
        }
    }

    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(CredentialsDto credentialsDto) {
        return new UsernamePasswordAuthenticationToken(credentialsDto.getMail(), credentialsDto.getPassword());
    }

    @Transactional
    public void register(CredentialsDto credentialsDto) {
        customerUserRepository.getCustomerUserByMail(credentialsDto.getMail())
                .ifPresent(customer -> {
                    logger.info("Not registering because account with given mail exists");
                    throw new EmailAlreadyRegisteredException();
                });

        customerUserRepository.save(newCustomerUser(credentialsDto));
        logger.info("Created new user");
        mailApplicationService.sendConfirmRegisterMail(credentialsDto.getMail());
    }

    private CustomerUser newCustomerUser(CredentialsDto credentialsDto) {
        final String hashedPassword = passwordEncoder.encode(credentialsDto.getPassword());
        return new CustomerUser(credentialsDto.getMail(), hashedPassword, ROLE_USER);
    }

    public void sendMailResettingPassword(EmailDto emailDto) {
        mailApplicationService.sendPasswordResettingMail(emailDto.getMail());
    }
}
