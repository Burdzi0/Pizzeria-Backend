package edu.pwr.pizzeria.model.pizza.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.OrderedIngredient;
import edu.pwr.pizzeria.model.pizza.Ingredient;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;

import java.math.BigDecimal;

public class PizzaIngredientDto {

    private int id;
    private String name;
    private BigDecimal price;
    private boolean ifAllergen;
    private int quantity;

    @JsonCreator
    public PizzaIngredientDto(@JsonProperty("id") int id,
                              @JsonProperty("name") String name,
                              @JsonProperty("price") BigDecimal price,
                              @JsonProperty("allergen") boolean ifAllergen,
                              @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ifAllergen = ifAllergen;
        this.quantity = quantity;
    }

    public static PizzaIngredientDto toDto(OrderedIngredient pizzaIngredient) {
        return new PizzaIngredientDto(pizzaIngredient.getId(), pizzaIngredient.getName(), pizzaIngredient.getPrice(), pizzaIngredient.isIfAllergen(), pizzaIngredient.getQuantity());
    }

    public static PizzaIngredientDto toDto(PizzaIngredient pizzaIngredient) {
        final Ingredient ingredient = pizzaIngredient.getIngredient();
        return new PizzaIngredientDto(ingredient.getId(), ingredient.getName(), ingredient.getPrice(), ingredient.isIfAllergen(), pizzaIngredient.getQuantity());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isIfAllergen() {
        return ifAllergen;
    }

    public void setIfAllergen(boolean ifAllergen) {
        this.ifAllergen = ifAllergen;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
