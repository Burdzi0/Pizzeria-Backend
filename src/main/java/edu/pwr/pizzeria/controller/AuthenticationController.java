package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.exception.InvalidLoginCredentialsException;
import edu.pwr.pizzeria.model.authentication.CredentialsDto;
import edu.pwr.pizzeria.model.authentication.TokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody CredentialsDto credentialsDto) {
        if (!isValidMail(credentialsDto.getMail()) || !isValidPassword(credentialsDto.getPassword())) {
            throw new InvalidLoginCredentialsException();
        }
        return new ResponseEntity<>(new TokenDto("SECRET_TOKEN"), HttpStatus.OK);
    }

    private boolean isValidPassword(String password) {
        return password.equals("password");
    }

    private boolean isValidMail(String mail) {
        return mail.equals("test@test.com");
    }
}
