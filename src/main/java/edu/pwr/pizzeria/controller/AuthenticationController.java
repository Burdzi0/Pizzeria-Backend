package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.exception.NotMailException;
import edu.pwr.pizzeria.model.authentication.CredentialsDto;
import edu.pwr.pizzeria.model.authentication.EmailDto;
import edu.pwr.pizzeria.model.authentication.TokenDto;
import edu.pwr.pizzeria.service.authentication.AuthenticationApplicationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1")
public class AuthenticationController {

    @Value("${app.link.login}")
    private String loginHref;

    private AuthenticationApplicationService authenticationService;

    public AuthenticationController(AuthenticationApplicationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ApiOperation(value = "Login user", notes = "Used to get a JWT token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User logged in successfully", response = TokenDto.class),
            @ApiResponse(code = 401, message = "Invalid credentials or username not found"),
            @ApiResponse(code = 400, message = "Request not valid (possibly email constraint)")
    })
    @PostMapping("/login")
    public TokenDto login(@ApiParam(value = "Obligatory credentials", required = true)
                          @Valid @RequestBody CredentialsDto credentialsDto, Errors errors) {
        validateIncomingDto(errors);
        return authenticationService.login(credentialsDto);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 409, message = "Email already registered"),
            @ApiResponse(code = 400, message = "Request not valid (possibly email constraint)")
    })
    @ApiOperation(value = "Register user", notes = "Create user with given credentials")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@ApiParam(value = "Obligatory credentials", required = true)
                         @Valid @RequestBody CredentialsDto credentialsDto, Errors errors, HttpServletRequest request) {
        validateIncomingDto(errors);
        authenticationService.register(credentialsDto, request);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Request not valid (possibly email constraint)")
    })
    @ApiOperation(value = "Confirm registration", notes = "Confirm registration and enables user")
    @GetMapping("/register/{unique-number}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> confirmRegister(@PathVariable("unique-number") UUID uuid) {
        authenticationService.confirmRegistration(uuid);
        final HttpHeaders httpHeaders = redirectUri();
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    private HttpHeaders redirectUri() {
        final URI redirectUri;
        try {
            redirectUri = new URI(loginHref);
            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUri);
            return httpHeaders;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Wrong Uri!");
        }
    }

    @ApiResponses({
            @ApiResponse(code = 202, message = "Sending mail"),
            @ApiResponse(code = 404, message = "Email not found")
    })
    @ApiOperation(value = "Register user", notes = "Create user with given credentials")
    @PostMapping("/reset")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void resetMail(@Valid @RequestBody EmailDto emailDto) {
        authenticationService.resetPassword(emailDto);
    }

    private void validateIncomingDto(Errors errors) {
        if (errors.hasErrors()) {
            throw new NotMailException("Invalid credentials");
        }
    }
}
