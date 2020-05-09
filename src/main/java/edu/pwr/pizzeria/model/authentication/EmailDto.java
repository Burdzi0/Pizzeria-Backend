package edu.pwr.pizzeria.model.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;

public class EmailDto {

    @Email
    private String mail;

    @JsonCreator
    public EmailDto(@JsonProperty("mail") String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
