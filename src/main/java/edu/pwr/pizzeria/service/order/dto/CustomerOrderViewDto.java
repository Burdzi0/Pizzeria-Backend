package edu.pwr.pizzeria.service.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;
import edu.pwr.pizzeria.model.user.Address;
import edu.pwr.pizzeria.model.user.AddressDto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerOrderViewDto {

    private long orderId;
    private Instant date;
    private List<PizzaContentDto> pizzas;
    private CustomerOrderStatus status;
    private AddressDto address;
    private double price;

    @JsonCreator
    public CustomerOrderViewDto(@JsonProperty("orderId") long orderId,
                                @JsonProperty("date") Instant date,
                                @JsonProperty("pizzas") List<PizzaContentDto> pizzas,
                                @JsonProperty("status") CustomerOrderStatus status,
                                @JsonProperty("address") AddressDto address,
                                @JsonProperty("price") double price) {
        this.orderId = orderId;
        this.date = date;
        this.pizzas = pizzas;
        this.status = status;
        this.address = address;
        this.price = price;
    }

    public static CustomerOrderViewDto toDto(CustomerOrder customerOrder) {
        final List<PizzaContentDto> contents = Stream.concat(customerOrder.getPizzas()
                        .stream()
                        .map(PizzaContentDto::toDto),
                customerOrder.getCustoms()
                        .stream()
                        .map(PizzaContentDto::toDto))
                .collect(Collectors.toUnmodifiableList());

        return new CustomerOrderViewDto(customerOrder.getId(), customerOrder.getDate(), contents, customerOrder.getStatus(), AddressDto.toDto(customerOrder.getAddress()), customerOrder.getTotal().doubleValue());
    }

    public long getOrderId() {
        return orderId;
    }

    public Instant getDate() {
        return date;
    }

    public List<PizzaContentDto> getPizzas() {
        return pizzas;
    }

    public CustomerOrderStatus getStatus() {
        return status;
    }

    public AddressDto getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }
}
