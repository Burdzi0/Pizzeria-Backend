package edu.pwr.pizzeria.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class CustomerUser extends User {

    @Column(nullable = false)
    private boolean active;

    public CustomerUser() {
        this.active = false;
    }

    public CustomerUser(String mail, String password, Role roles) {
        super(mail, password, roles);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
