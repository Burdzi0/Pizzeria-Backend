package edu.pwr.pizzeria.model.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;

public class CredentialsDto {

    @Email
    private String mail;
    private String password;

    @JsonCreator
    public CredentialsDto(@JsonProperty("mail") String mail,
                          @JsonProperty("password") String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
