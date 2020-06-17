package edu.pwr.pizzeria.model.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_USER, ROLE_COOK, ROLE_DELIVERY;

    public String getAuthority() {
        return name();
    }

}
