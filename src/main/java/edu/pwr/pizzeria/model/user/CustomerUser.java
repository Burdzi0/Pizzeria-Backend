package edu.pwr.pizzeria.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class CustomerUser extends User {

    @Column(nullable = false)
    private boolean active;

    @OneToOne
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerUser that = (CustomerUser) o;
        return active == that.active &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, address);
    }
}
