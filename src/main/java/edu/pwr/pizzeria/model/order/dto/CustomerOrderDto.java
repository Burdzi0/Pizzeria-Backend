package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.user.AddressDto;

import java.util.List;

public class CustomerOrderDto {

    private final List<StandardPizzaDto> standards;
    private final List<CustomPizzaDto> customs;
    private final AddressDto address;
    private final String payment;

    @JsonCreator
    public CustomerOrderDto(@JsonProperty("standards") List<StandardPizzaDto> standards,
                            @JsonProperty("customs") List<CustomPizzaDto> customs,
                            @JsonProperty("address") AddressDto address,
                            @JsonProperty("payment") String payment) {
        this.standards = standards;
        this.customs = customs;
        this.address = address;
        this.payment = payment;
    }

    public List<StandardPizzaDto> getStandards() {
        return standards;
    }

    public List<CustomPizzaDto> getCustoms() {
        return customs;
    }

    public AddressDto getAddress() {
        return address;
    }

    public String getPayment() { return payment; }
}
