package edu.pwr.pizzeria.service.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.OrderedPizza;
import edu.pwr.pizzeria.model.pizza.PizzaCrust;
import edu.pwr.pizzeria.model.pizza.dto.PizzaIngredientDto;

import java.util.List;
import java.util.stream.Collectors;

public class PizzaContentDto {

    private final int diameter;
    private final PizzaCrust crust;
    private final List<PizzaIngredientDto> pizzaIngredientDto;

    @JsonCreator
    public PizzaContentDto(@JsonProperty("diameter") int diameter,
                           @JsonProperty("crust") PizzaCrust crust,
                           @JsonProperty("ingredients") List<PizzaIngredientDto> pizzaIngredientDto) {
        this.diameter = diameter;
        this.crust = crust;
        this.pizzaIngredientDto = pizzaIngredientDto;
    }

    public static PizzaContentDto toDto(OrderedPizza pizza) {
        return new PizzaContentDto(pizza.getDiameter(), pizza.getCrust(),
                pizza.getIngredients()
                        .stream()
                        .map(PizzaIngredientDto::toDto)
                        .collect(Collectors.toUnmodifiableList())
        );
    }

    public static PizzaContentDto toDto(CustomPizza pizza) {
        return new PizzaContentDto(pizza.getDiameter(), pizza.getCrust(),
                pizza.getPizzaIngredients()
                        .stream()
                        .map(PizzaIngredientDto::toDto)
                        .collect(Collectors.toUnmodifiableList())
        );
    }

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }

    public List<PizzaIngredientDto> getPizzaIngredientDto() {
        return pizzaIngredientDto;
    }
}
