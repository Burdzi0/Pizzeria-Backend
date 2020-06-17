package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.OrderedIngredient;

import java.math.BigDecimal;

public class OrderedIngredientDto {

    private int id;
    private String name;
    private BigDecimal price;
    private boolean ifAllergen;
    private int quantity;

    @JsonCreator
    public OrderedIngredientDto(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("ifAllergen") boolean ifAllergen,
            @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ifAllergen = ifAllergen;
        this.quantity = quantity;
    }

    public static OrderedIngredientDto toDto(OrderedIngredient orderedIngredient) {
        return new OrderedIngredientDto(orderedIngredient.getId(),
                orderedIngredient.getName(),
                orderedIngredient.getPrice(),
                orderedIngredient.isIfAllergen(),
                orderedIngredient.getQuantity());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
