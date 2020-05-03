package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.pizza.dto.CustomPizzaDto;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerOrderDto {

    private List<Integer> pizzas;
    private List<CustomPizzaDto> customPizzas;

    @JsonCreator
    public CustomerOrderDto(
            @JsonProperty("pizzas") List<Integer> pizzas,
            @JsonProperty("customPizzas") List<CustomPizzaDto> customPizzas) {
        this.pizzas = pizzas;
        this.customPizzas = customPizzas;
    }

    public List<Integer> getPizzas() {
        return pizzas;
    }

    public List<CustomPizzaDto> getCustomPizzas() {
        return customPizzas;
    }
}
