package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.user.Address;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CustomerOrderDto {

    private final Long id;
    private final Address address;
    private final List<Pizza> pizzas;
    private final BigDecimal total;
    private final Date date;
    private final Time timePassed;
    private final CustomerOrderStatus customerOrderStatus;
    private final String notes;

    @JsonCreator
    public CustomerOrderDto(@JsonProperty("id") Long id,
                            @JsonProperty("address") Address address,
                            @JsonProperty("pizzas") List<Pizza> pizzas,
                            @JsonProperty("total") BigDecimal total,
                            @JsonProperty("date") Date date,
                            @JsonProperty("time") Time timePassed,
                            @JsonProperty("customOrderStatus") CustomerOrderStatus customerOrderStatus,
                            @JsonProperty("notes") String notes) {
        this.id = id;
        this.address = address;
        this.pizzas = pizzas;
        this.total = total;
        this.date = date;
        this.timePassed = timePassed;
        this.customerOrderStatus = customerOrderStatus;
        this.notes = notes;
    }

    public static CustomerOrderDto toDto(CustomerOrder customerOrder) {
        return new CustomerOrderDto(customerOrder.getId(),
                customerOrder.getAddress(),
                customerOrder.getPizzas(),
                customerOrder.getTotal(),
                customerOrder.getDate(),
                customerOrder.getTimePassed(),
                customerOrder.getStatus(),
                customerOrder.getNotes());
    }

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

    public Time getTimePassed() {
        return timePassed;
    }

    public CustomerOrderStatus getCustomerOrderStatus() {
        return customerOrderStatus;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrderDto that = (CustomerOrderDto) o;
        return id.equals(that.id) &&
                address.equals(that.address) &&
                pizzas.equals(that.pizzas) &&
                Objects.equals(total, that.total) &&
                Objects.equals(date, that.date) &&
                Objects.equals(timePassed, that.timePassed) &&
                customerOrderStatus == that.customerOrderStatus &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, pizzas, total, date, timePassed, customerOrderStatus, notes);
    }

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
                "id=" + id +
                ", address=" + address +
                ", pizzas=" + pizzas +
                ", total=" + total +
                ", date=" + date +
                ", timePassed=" + timePassed +
                ", customerOrderStatus=" + customerOrderStatus +
                ", notes='" + notes + '\'' +
                '}';
    }
}
