package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.cook.CookingOrderStatus;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.user.CustomerUser;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CookViewOrderDto {

    private int id;
    private CustomerUser customerUser;
    private Date date;
    private Time timePassed;
    private String notes;
    private List<Pizza> pizzas;
    private CookingOrderStatus status;

    @JsonCreator
    public CookViewOrderDto(@JsonProperty("id") int id,
                            @JsonProperty("customerUser") CustomerUser customerUser,
                            @JsonProperty("date") Date date,
                            @JsonProperty("timePassed") Time timePassed,
                            @JsonProperty("notes") String notes,
                            @JsonProperty("pizzas") List<Pizza> pizzas,
                            @JsonProperty("status") CookingOrderStatus status){
        this.id = id;
        this.customerUser = customerUser;
        this.date = date;
        this.timePassed = timePassed;
        this.notes = notes;
        this.pizzas = pizzas;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public CustomerUser getCustomerUser() {
        return customerUser;
    }

    public Date getDate() {
        return date;
    }

    public Time getTimePassed() {
        return timePassed;
    }

    public String getNotes() {
        return notes;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public CookingOrderStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookViewOrderDto that = (CookViewOrderDto) o;
        return id == that.id &&
                customerUser.equals(that.customerUser) &&
                Objects.equals(date, that.date) &&
                Objects.equals(timePassed, that.timePassed) &&
                Objects.equals(notes, that.notes) &&
                pizzas.equals(that.pizzas) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerUser, date, timePassed, notes, pizzas, status);
    }

    @Override
    public String toString() {
        return "CookViewOrderDto{" +
                "id=" + id +
                ", customerUser=" + customerUser +
                ", date=" + date +
                ", timePassed=" + timePassed +
                ", notes='" + notes + '\'' +
                ", pizzas=" + pizzas +
                ", status=" + status +
                '}';
    }
}
