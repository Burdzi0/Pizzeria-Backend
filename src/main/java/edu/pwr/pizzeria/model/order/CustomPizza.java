package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.pizza.PizzaCrust;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;

import javax.persistence.*;
import java.util.List;

@Entity
public class CustomPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int diameter;
    private PizzaCrust crust;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PizzaIngredient> pizzaIngredients;

    public CustomPizza() {
    }

    public CustomPizza(int diameter, PizzaCrust crust, List<PizzaIngredient> pizzaIngredients) {
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

    public List<PizzaIngredient> getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(List<PizzaIngredient> pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }
}
