package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.pizza.StandardPizza;
import edu.pwr.pizzeria.model.user.Address;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<StandardPizza> standardPizzas;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CustomPizza> customs;

    @OneToOne(cascade = CascadeType.ALL)
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

    public List<StandardPizza> getStandardPizzas() {
        return standardPizzas;
    }

    public void setStandardPizzas(List<StandardPizza> standardPizzas) {
        this.standardPizzas = standardPizzas;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(standardPizzas, that.standardPizzas) &&
                Objects.equals(customs, that.customs) &&
                Objects.equals(address, that.address) &&
                Objects.equals(total, that.total) &&
                Objects.equals(date, that.date) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, standardPizzas, customs, address, total, date, status);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", standardPizzas=" + standardPizzas +
                ", customs=" + customs +
                ", address=" + address +
                ", total=" + total +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}

