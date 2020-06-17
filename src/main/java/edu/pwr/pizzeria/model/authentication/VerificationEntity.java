package edu.pwr.pizzeria.model.authentication;

import edu.pwr.pizzeria.model.user.CustomerUser;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class VerificationEntity {

    @Id
    private UUID id;

    @OneToOne(targetEntity = CustomerUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private CustomerUser user;

    public VerificationEntity() {
    }

    public VerificationEntity(UUID id, CustomerUser user) {
        this.id = id;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CustomerUser getUser() {
        return user;
    }

    public void setUser(CustomerUser user) {
        this.user = user;
    }
}
