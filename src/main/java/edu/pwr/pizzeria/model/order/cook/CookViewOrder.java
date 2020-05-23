package edu.pwr.pizzeria.model.order.cook;

import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.pizza.Pizza;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class CookViewOrder extends CustomerOrder {

    @OneToMany
    private List<Pizza> pizzas;
    private CookingOrderStatus status;

    public CookViewOrder() {
        super();
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public CookingOrderStatus getStatus() {
        return status;
    }

    public void setStatus(CookingOrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CookViewOrder that = (CookViewOrder) o;
        return pizzas.equals(that.pizzas) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pizzas, status);
    }

    @Override
    public String toString() {
        return "CookViewOrder{" +
                "pizzas=" + pizzas +
                ", status=" + status +
                ", id=" + id +
                ", customerUser=" + customerUser +
                ", date=" + date +
                ", timePassed=" + timePassed +
                ", notes='" + notes + '\'' +
                '}';
    }
}

