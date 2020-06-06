package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.pizza.PizzaCrust;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class CustomPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int diameter;
    private PizzaCrust crust;
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderedIngredient> getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(List<OrderedIngredient> pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomPizza that = (CustomPizza) o;
        return diameter == that.diameter &&
                Objects.equals(id, that.id) &&
                crust == that.crust &&
                Objects.equals(price, that.price) &&
                Objects.equals(pizzaIngredients, that.pizzaIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diameter, crust, price, pizzaIngredients);
    }

    @Override
    public String toString() {

        String ingredients = pizzaIngredients.stream().map(i -> i.toString()).collect(Collectors.toList()).toString();
        return "Twoja pizza: " + crust + " ciasto, średnica: " + diameter + " cm, składniki: " + ingredients;
    }
}
