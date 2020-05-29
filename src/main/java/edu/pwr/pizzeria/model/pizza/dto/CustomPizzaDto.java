package edu.pwr.pizzeria.model.pizza.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.pizza.PizzaCrust;

import java.util.List;

public class CustomPizzaDto {

    private final int diameter;
    private final PizzaCrust crust;
    private final List<PizzaIngredient2Dto> pizzaIngredientDtos;

    @JsonCreator
    public CustomPizzaDto(@JsonProperty("diameter") int diameter,
                          @JsonProperty("crust") PizzaCrust crust,
                          @JsonProperty("pizzaIngredients") List<PizzaIngredient2Dto> pizzaIngredientDtos) {
        this.diameter = diameter;
        this.crust = crust;
        this.pizzaIngredientDtos = pizzaIngredientDtos;
    }

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }

    public List<PizzaIngredient2Dto> getPizzaIngredientDtos() {
        return pizzaIngredientDtos;
    }
}
