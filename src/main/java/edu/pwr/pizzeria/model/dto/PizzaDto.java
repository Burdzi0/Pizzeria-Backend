package edu.pwr.pizzeria.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.Pizza;
import edu.pwr.pizzeria.model.PizzaCrust;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PizzaDto {

    private final int id;
    private final String typeName;
    private final List<IngredientDto> ingredients;
    private final int diameter;
    private final PizzaCrust crust;

    @JsonCreator
    public PizzaDto(@JsonProperty("id") int id,
                    @JsonProperty("typeName") String typeName,
                    @JsonProperty("ingredients") List<IngredientDto> ingredients,
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
                toIngredientsDtos(pizza),
                pizza.getDiameter(),
                pizza.getCrust());
    }

    private static List<IngredientDto> toIngredientsDtos(Pizza pizza) {
        return pizza.getIngredients().stream()
                .map(IngredientDto::toDto).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaDto pizzaDto = (PizzaDto) o;
        return id == pizzaDto.id &&
                diameter == pizzaDto.diameter &&
                Objects.equals(typeName, pizzaDto.typeName) &&
                Objects.equals(ingredients, pizzaDto.ingredients) &&
                crust == pizzaDto.crust;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, ingredients, diameter, crust);
    }

    @Override
    public String toString() {
        return "PizzaDto{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", ingredients=" + ingredients +
                ", diameter=" + diameter +
                ", crust=" + crust +
                '}';
    }
}
