package edu.pwr.pizzeria.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.Ingredient;
import edu.pwr.pizzeria.model.PizzaIngredient;

public class PizzaIngredientDto {

    private final int id;
    private final Ingredient ingredient;
    private final int quantity;

    @JsonCreator
    public PizzaIngredientDto(@JsonProperty("id") int id,
                              @JsonProperty("ingredient") Ingredient ingredient,
                              @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public static PizzaIngredientDto toDto(PizzaIngredient pizzaIngredient) {

        return new PizzaIngredientDto(pizzaIngredient.getId(),
                pizzaIngredient.getIngredient(),
                pizzaIngredient.getQuantity());
    }

    public int getId() {
        return id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }
}
