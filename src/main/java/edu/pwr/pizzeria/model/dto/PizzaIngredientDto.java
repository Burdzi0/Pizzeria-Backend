package edu.pwr.pizzeria.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
<<<<<<< HEAD
import edu.pwr.pizzeria.model.Ingredient;
=======
>>>>>>> b34fd8a178299ea81edd665113315b01e0b68b6e
import edu.pwr.pizzeria.model.PizzaIngredient;

public class PizzaIngredientDto {

    private final int id;
    private final IngredientDto ingredient;
    private final int quantity;

    @JsonCreator
    public PizzaIngredientDto(@JsonProperty("id") int id,
                              @JsonProperty("ingredient") IngredientDto ingredient,
                              @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public static PizzaIngredientDto toDto(PizzaIngredient pizzaIngredient) {

        return new PizzaIngredientDto(pizzaIngredient.getId(),
                IngredientDto.toDto(pizzaIngredient.getIngredient()),
                pizzaIngredient.getQuantity());
    }

    public int getId() {
        return id;
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }
}
