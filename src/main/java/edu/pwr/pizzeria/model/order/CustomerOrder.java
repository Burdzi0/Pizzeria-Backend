package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.user.Address;
import edu.pwr.pizzeria.model.user.CustomerUser;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderedPizza> pizzas;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CustomPizza> customs;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne
    private CustomerUser customerUser;

    private BigDecimal total;
    private String payment;
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

    public List<OrderedPizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<OrderedPizza> pizzas) {
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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

    public CustomerUser getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(CustomerUser customerUser) {
        this.customerUser = customerUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pizzas, that.pizzas) &&
                Objects.equals(customs, that.customs) &&
                Objects.equals(address, that.address) &&
                Objects.equals(customerUser, that.customerUser) &&
                Objects.equals(total, that.total) &&
                Objects.equals(payment, that.payment) &&
                Objects.equals(date, that.date) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pizzas, customs, address, customerUser, total, payment, date, status);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", pizzas=" + pizzas +
                ", customs=" + customs +
                ", address=" + address +
                ", customerUser=" + customerUser +
                ", total=" + total +
                ", payment='" + payment + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}

