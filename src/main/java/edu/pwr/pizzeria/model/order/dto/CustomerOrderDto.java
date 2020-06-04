package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.OrderedPizza;
import edu.pwr.pizzeria.model.user.AddressDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CustomerOrderDto {

    private final List<StandardPizzaDto> standards;
    private final List<CustomPizzaDto> customs;
    private final AddressDto address;

    @JsonCreator
    public CustomerOrderDto(@JsonProperty("standard") List<StandardPizzaDto> standards,
                            @JsonProperty("customs") List<CustomPizzaDto> customs,
                            @JsonProperty("address") AddressDto address) {
        this.standards = standards;
        this.customs = customs;
        this.address = address;
    }

    public List<StandardPizzaDto> getStandards() {
        return standards;
    }

    public List<CustomPizzaDto> getCustoms() {
        return customs;
    }

    public AddressDto getAddressDto() {
        return address;
    }

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
                "pizzas=" + standards +
                ", customs=" + customs +
                ", addressDto=" + address +
                '}';
    }
}
