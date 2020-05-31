package edu.pwr.pizzeria.model.pizza.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.pizza.StandardPizza;
import edu.pwr.pizzeria.model.pizza.PizzaCrust;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaDto {

    private final int id;
    private final String typeName;
    private final List<PizzaIngredientDto> ingredients;
    private final BigDecimal price;
    private final int diameter;
    private final PizzaCrust crust;

    @JsonCreator
    public PizzaDto(@JsonProperty("id") int id,
                    @JsonProperty("typeName") String typeName,
                    @JsonProperty("ingredients") List<PizzaIngredientDto> ingredients,
                    @JsonProperty("price") BigDecimal price,
                    @JsonProperty("diameter") int diameter,
                    @JsonProperty("crust") PizzaCrust crust) {

        this.id = id;
        this.typeName = typeName;
        this.ingredients = ingredients;
        this.price = price;
        this.diameter = diameter;
        this.crust = crust;
    }

    public static PizzaDto toDto(StandardPizza standardPizza) {
        return new PizzaDto(standardPizza.getId(),
                standardPizza.getTypeName(),
                pizzaIngredientsToDto(standardPizza.getIngredients()),
                standardPizza.getPrice(),
                standardPizza.getDiameter(),
                standardPizza.getCrust());
    }

    private static List<PizzaIngredientDto> pizzaIngredientsToDto(List<PizzaIngredient> pizzaIngredients) {
        return pizzaIngredients
                .stream()
                .map(PizzaIngredientDto::toDto)
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public List<PizzaIngredientDto> getPizzaIngredients() {
        return ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }
}
