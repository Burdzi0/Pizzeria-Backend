package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.user.CustomerUser;

import javax.persistence.*;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private CustomerUser customerUser;

    public CustomerOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerUser getCustomerUser() {
        return customerUser;
    }
}
