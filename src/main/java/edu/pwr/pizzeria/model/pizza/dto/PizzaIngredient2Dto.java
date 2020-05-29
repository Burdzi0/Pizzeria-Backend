package edu.pwr.pizzeria.model.pizza.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PizzaIngredient2Dto {

    private final int id;
    private final int quantity;

    @JsonCreator
    public PizzaIngredient2Dto(@JsonProperty("id")int id,
                               @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
