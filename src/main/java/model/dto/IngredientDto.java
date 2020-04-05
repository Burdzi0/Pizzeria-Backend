package model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Ingredient;

import java.math.BigDecimal;

public class IngredientDto {

    private final String name;
    private final int quantity;
    private final BigDecimal price;
    private final boolean ifAllergen;

    @JsonCreator
    public IngredientDto(@JsonProperty("name") String name,
                         @JsonProperty("qunatity") int quantity,
                         @JsonProperty("price") BigDecimal price,
                         @JsonProperty("ifAllergen") boolean ifAllergen) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.ifAllergen = ifAllergen;
    }

    public static IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getName(),
                ingredient.getQuantity(),
                ingredient.getPrice(),
                ingredient.isIfAllergen());
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isIfAllergen() {
        return ifAllergen;
    }
}
