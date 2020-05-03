package edu.pwr.pizzeria.model.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_CUSTOMER, ROLE_USER;

    public String getAuthority() {
        return name();
    }

}
