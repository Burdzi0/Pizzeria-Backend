package edu.pwr.pizzeria.model.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CredentialsDto {

    private final String mail;
    private final String password;

    @JsonCreator
    public CredentialsDto(@JsonProperty("mail") String mail,
                          @JsonProperty("password") String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
