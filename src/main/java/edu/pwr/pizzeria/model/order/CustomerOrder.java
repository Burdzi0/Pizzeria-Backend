package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.user.CustomerUser;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private CustomerUser customerUser;

    @OneToMany
    private List<Pizza> pizzas;
    private BigDecimal total;
    private Date date;
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerUser getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(CustomerUser customerUser) {
        this.customerUser = customerUser;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return id.equals(that.id) &&
                customerUser.equals(that.customerUser) &&
                pizzas.equals(that.pizzas) &&
                Objects.equals(total, that.total) &&
                date.equals(that.date) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerUser, pizzas, total, date, status);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", customerUser=" + customerUser +
                ", pizzas=" + pizzas +
                ", total=" + total +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
