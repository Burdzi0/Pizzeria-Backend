package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.pizza.PizzaCrust;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static edu.pwr.pizzeria.model.pizza.Pizza.THICK_CRUST_PRICE;
import static edu.pwr.pizzeria.model.pizza.Pizza.THIN_CRUST_PRICE;

@Entity
public class CustomPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int diameter;
    private PizzaCrust crust;
    private BigDecimal price20;
    private BigDecimal price30;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderedIngredient> pizzaIngredients;

    public CustomPizza() {
    }

    public CustomPizza(int diameter, PizzaCrust crust, List<OrderedIngredient> pizzaIngredients) {
        this.diameter = diameter;
        this.crust = crust;
        this.pizzaIngredients = pizzaIngredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }

    public void setCrust(PizzaCrust crust) {
        this.crust = crust;
    }

    public BigDecimal getPrice20() {
        return price20;
    }

    public BigDecimal getPrice30() {
        return price30;
    }

    public List<OrderedIngredient> getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(List<OrderedIngredient> pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }

    public void computePrice() {

        double sum = 0.0;

        switch(crust){
            case GRUBE:
                sum += THICK_CRUST_PRICE;
            case CIENKIE:
                sum += THIN_CRUST_PRICE;
        }

        for (OrderedIngredient ingredient : pizzaIngredients) {
            sum += ingredient.getPrice().doubleValue() * ingredient.getQuantity();
        }

        price20 = BigDecimal.valueOf(sum);
        price30 = BigDecimal.valueOf(sum *1.5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomPizza that = (CustomPizza) o;
        return diameter == that.diameter &&
                Objects.equals(id, that.id) &&
                crust == that.crust &&
                Objects.equals(price20, that.price20) &&
                Objects.equals(price30, that.price30) &&
                Objects.equals(pizzaIngredients, that.pizzaIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diameter, crust, price20, price30, pizzaIngredients);
    }

    @Override
    public String toString() {
        return "CustomPizza{" +
                "id=" + id +
                ", diameter=" + diameter +
                ", crust=" + crust +
                ", price20=" + price20 +
                ", price30=" + price30 +
                ", pizzaIngredients=" + pizzaIngredients +
                '}';
    }
}
