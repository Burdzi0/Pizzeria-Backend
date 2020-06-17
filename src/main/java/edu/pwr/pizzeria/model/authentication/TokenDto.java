package edu.pwr.pizzeria.model.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TokenDto {

    private final String token;
    private final Collection<? extends GrantedAuthority> roles;

    @JsonCreator
    public TokenDto(@JsonProperty("token") String token, @JsonProperty("roles") Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }
}
