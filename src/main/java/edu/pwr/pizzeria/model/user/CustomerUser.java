package edu.pwr.pizzeria.model.user;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class CustomerUser extends User {

    public CustomerUser() {
    }

    public CustomerUser(String mail, String password, Role roles) {
        super(mail, password, roles);
    }
}
