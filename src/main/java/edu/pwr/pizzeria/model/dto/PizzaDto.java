package edu.pwr.pizzeria.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.Pizza;
import edu.pwr.pizzeria.model.PizzaCrust;
import edu.pwr.pizzeria.model.PizzaIngredient;

import java.util.List;

public class PizzaDto {

    private final int id;
    private final String typeName;
    private final List<PizzaIngredient> ingredients;
    private final int diameter;
    private final PizzaCrust crust;

    @JsonCreator
    public PizzaDto(@JsonProperty("id") int id,
                    @JsonProperty("typeName") String typeName,
                    @JsonProperty("ingredients") List<PizzaIngredient> ingredients,
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
                pizza.getPizzaIngredients(),
                pizza.getDiameter(),
                pizza.getCrust());
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public List<PizzaIngredient> getPizzaIngredients() {
        return ingredients;
    }

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }
}
