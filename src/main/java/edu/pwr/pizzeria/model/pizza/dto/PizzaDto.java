package edu.pwr.pizzeria.model.pizza.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.pizza.PizzaCrust;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaDto {

    private final int id;
    private final String typeName;
    private final List<PizzaIngredientDto> ingredients;
    private final BigDecimal price20;
    private final BigDecimal price30;
    private final int diameter;
    private final PizzaCrust crust;

    @JsonCreator
    public PizzaDto(@JsonProperty("id") int id,
                    @JsonProperty("typeName") String typeName,
                    @JsonProperty("ingredients") List<PizzaIngredientDto> ingredients,
                    @JsonProperty("price20") BigDecimal price20,
                    @JsonProperty("price30") BigDecimal price30,
                    @JsonProperty("diameter") int diameter,
                    @JsonProperty("crust") PizzaCrust crust) {

        this.id = id;
        this.typeName = typeName;
        this.ingredients = ingredients;
        this.price20 = price20;
        this.price30 = price30;
        this.diameter = diameter;
        this.crust = crust;
    }

    public static PizzaDto toDto(Pizza pizza) {
        return new PizzaDto(pizza.getId(),
                pizza.getTypeName(),
                pizzaIngredientsToDto(pizza.getPizzaIngredients()),
                pizza.getPrice20(),
                pizza.getPrice30(),
                pizza.getDiameter(),
                pizza.getCrust());
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

    public BigDecimal getPrice20() { return price20; }

    public BigDecimal getPrice30() { return price30; }

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }
}
