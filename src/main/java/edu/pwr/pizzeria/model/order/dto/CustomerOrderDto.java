package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.pizza.dto.CustomPizzaDto;
import edu.pwr.pizzeria.model.pizza.dto.StandardPizzaDto;
import edu.pwr.pizzeria.model.user.AddressDto;

import java.util.List;

public class CustomerOrderDto {

    private final List<StandardPizzaDto> pizzas;
    private final List<CustomPizzaDto> customPizzaDtos;
    private final AddressDto addressDto;

    @JsonCreator
    public CustomerOrderDto(@JsonProperty("standard") List<StandardPizzaDto> pizzas,
                            @JsonProperty("customs") List<CustomPizzaDto> customPizzaDtos,
                            @JsonProperty("address") AddressDto addressDto) {
        this.pizzas = pizzas;
        this.customPizzaDtos = customPizzaDtos;
        this.addressDto = addressDto;
    }

    public List<StandardPizzaDto> getPizzas() {
        return pizzas;
    }

    public List<CustomPizzaDto> getCustomPizzaDtos() {
        return customPizzaDtos;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
                "pizzas=" + pizzas +
                ", customPizzaDtos=" + customPizzaDtos +
                ", addressDto=" + addressDto +
                '}';
    }
}
