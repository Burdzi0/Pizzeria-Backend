package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.user.AddressDto;

import java.util.List;

public class CustomerOrderDto {

    private final List<StandardPizzaDto> pizzas;
    private final List<CustomPizzaDto> customs;
    private final AddressDto addressDto;

    @JsonCreator
    public CustomerOrderDto(@JsonProperty("standard") List<StandardPizzaDto> pizzas,
                            @JsonProperty("customs") List<CustomPizzaDto> customs,
                            @JsonProperty("address") AddressDto addressDto) {
        this.pizzas = pizzas;
        this.customs = customs;
        this.addressDto = addressDto;
    }

    public List<StandardPizzaDto> getPizzas() {
        return pizzas;
    }

    public List<CustomPizzaDto> getCustoms() {
        return customs;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
                "pizzas=" + pizzas +
                ", customs=" + customs +
                ", addressDto=" + addressDto +
                '}';
    }
}
