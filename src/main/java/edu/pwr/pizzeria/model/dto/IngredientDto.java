package edu.pwr.pizzeria.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.Ingredient;
import java.math.BigDecimal;
import java.util.Objects;

public class IngredientDto {

    private final int id;
    private final String name;
    private final int quantity;
    private final BigDecimal price;
    private final boolean ifAllergen;

    @JsonCreator
    public IngredientDto(@JsonProperty("id") int id,
                         @JsonProperty("name") String name,
                         @JsonProperty("qunatity") int quantity,
                         @JsonProperty("price") BigDecimal price,
                         @JsonProperty("ifAllergen") boolean ifAllergen) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.ifAllergen = ifAllergen;
    }

    public static IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(),
                ingredient.getName(),
                ingredient.getQuantity(),
                ingredient.getPrice(),
                ingredient.isIfAllergen());
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientDto that = (IngredientDto) o;
        return id == that.id &&
                quantity == that.quantity &&
                ifAllergen == that.ifAllergen &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price, ifAllergen);
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", ifAllergen=" + ifAllergen +
                '}';
    }
}
