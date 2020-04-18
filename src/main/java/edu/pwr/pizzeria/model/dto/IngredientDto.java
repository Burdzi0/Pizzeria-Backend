package edu.pwr.pizzeria.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.Ingredient;
import java.math.BigDecimal;

public class IngredientDto {

    private final int id;
    private final String name;
    private final BigDecimal price;
    private final boolean ifAllergen;

    @JsonCreator
    public IngredientDto(@JsonProperty("id") int id,
                         @JsonProperty("name") String name,
                         @JsonProperty("price") BigDecimal price,
                         @JsonProperty("ifAllergen") boolean ifAllergen) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ifAllergen = ifAllergen;
    }

    public static IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(),
                ingredient.getName(),
                ingredient.getPrice(),
                ingredient.isIfAllergen());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isIfAllergen() {
        return ifAllergen;
    }
}
