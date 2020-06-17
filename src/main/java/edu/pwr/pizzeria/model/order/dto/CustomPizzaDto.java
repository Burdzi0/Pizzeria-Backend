package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.pizza.PizzaCrust;
import edu.pwr.pizzeria.model.pizza.dto.CustomPizzaIngredient;

import java.util.List;

public class CustomPizzaDto {

    private final int diameter;
    private final PizzaCrust crust;
    private final List<CustomPizzaIngredient> pizzaIngredients;

    @JsonCreator
    public CustomPizzaDto(@JsonProperty("diameter") int diameter,
                          @JsonProperty("crust") PizzaCrust crust,
                          @JsonProperty("pizzaIngredients") List<CustomPizzaIngredient> pizzaIngredients) {
        this.diameter = diameter;
        this.crust = crust;
        this.pizzaIngredients = pizzaIngredients;
    }

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }


    public List<CustomPizzaIngredient> getPizzaIngredients() {
        return pizzaIngredients;
    }

    @Override
    public String toString() {
        return "CustomPizzaDto{" +
                "diameter=" + diameter +
                ", crust=" + crust +
                ", pizzaIngredients=" + pizzaIngredients +
                '}';
    }
}
