package edu.pwr.pizzeria.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.Pizza;
import edu.pwr.pizzeria.model.PizzaCrust;
import edu.pwr.pizzeria.model.PizzaIngredient;

import java.util.List;
import java.util.stream.Collectors;

public class PizzaDto {

    private final int id;
    private final String typeName;
    private final List<PizzaIngredientDto> ingredients;
    private final int diameter;
    private final PizzaCrust crust;

    @JsonCreator
    public PizzaDto(@JsonProperty("id") int id,
                    @JsonProperty("typeName") String typeName,
                    @JsonProperty("ingredients") List<PizzaIngredientDto> ingredients,
                    @JsonProperty("diameter") int diameter,
                    @JsonProperty("crust") PizzaCrust crust) {

        this.id = id;
        this.typeName = typeName;
        this.ingredients = ingredients;
        this.diameter = diameter;
        this.crust = crust;
    }

    public static PizzaDto toDto(Pizza pizza) {
        return new PizzaDto(pizza.getId(),
                pizza.getTypeName(),
                pizzaIngredientsToDto(pizza.getPizzaIngredients()),
                pizza.getDiameter(),
                pizza.getCrust());
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

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }

    private static List<PizzaIngredientDto> pizzaIngredientsToDto(List<PizzaIngredient> pizzaIngredients){
        return pizzaIngredients
                .stream()
                .map(PizzaIngredientDto::toDto)
                .collect(Collectors.toList());
    }
}
