package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.user.Address;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Pizza> pizzas;

    @OneToMany
    private List<CustomPizza> customs;

    @OneToOne
    private Address address;

    private BigDecimal total;
    private Instant date;
    private CustomerOrderStatus status;


    public CustomerOrder() {
        status = CustomerOrderStatus.COOK_AWAITING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<CustomPizza> getCustoms() {
        return customs;
    }

    public void setCustoms(List<CustomPizza> customs) {
        this.customs = customs;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public CustomerOrderStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerOrderStatus status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

