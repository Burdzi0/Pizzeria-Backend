package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.user.Address;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public abstract class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    //???
    protected Address address;

    @OneToMany
    private List<Pizza> pizzas;
    private BigDecimal total;
    private Date date;
    private Time timePassed;
    private CustomerOrderStatus status;
    private String notes;

    public CustomerOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(Time timePassed) {
        this.timePassed = timePassed;
    }

    public CustomerOrderStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerOrderStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void computeTotal(){

        double sum = 0.0;

        for (Pizza pizza : pizzas) {
            sum += pizza.getPrice().doubleValue();
        }

        total = BigDecimal.valueOf(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return id.equals(that.id) &&
                address.equals(that.address) &&
                Objects.equals(pizzas, that.pizzas) &&
                Objects.equals(total, that.total) &&
                Objects.equals(date, that.date) &&
                Objects.equals(timePassed, that.timePassed) &&
                status == that.status &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, pizzas, total, date, timePassed, status, notes);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", address=" + address +
                ", pizzas=" + pizzas +
                ", total=" + total +
                ", date=" + date +
                ", timePassed=" + timePassed +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                '}';
    }
}

