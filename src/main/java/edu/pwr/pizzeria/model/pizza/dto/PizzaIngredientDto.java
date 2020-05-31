package edu.pwr.pizzeria.model.pizza.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;

public class PizzaIngredientDto {

    private final IngredientDto ingredient;
    private final int quantity;

    @JsonCreator
    public PizzaIngredientDto(@JsonProperty("ingredient") IngredientDto ingredient,
                              @JsonProperty("quantity") int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public static PizzaIngredientDto toDto(PizzaIngredient pizzaIngredient) {
        return new PizzaIngredientDto(IngredientDto.toDto(pizzaIngredient.getIngredient()),
                pizzaIngredient.getQuantity());
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }
}
