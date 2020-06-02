package edu.pwr.pizzeria.service.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerOrderViewDto {

    private long orderId;
    private Instant date;
    private List<PizzaContentDto> pizzas;
    private CustomerOrderStatus status;

    @JsonCreator
    public CustomerOrderViewDto(@JsonProperty("orderId") long orderId,
                                @JsonProperty("date") Instant date,
                                @JsonProperty("pizzas") List<PizzaContentDto> pizzas,
                                @JsonProperty("status") CustomerOrderStatus status) {
        this.orderId = orderId;
        this.date = date;
        this.pizzas = pizzas;
        this.status = status;
    }

    public static CustomerOrderViewDto toDto(CustomerOrder customerOrder) {
        final List<PizzaContentDto> contents = Stream.concat(customerOrder.getPizzas()
                        .stream()
                        .map(PizzaContentDto::toDto),
                customerOrder.getCustoms()
                        .stream()
                        .map(PizzaContentDto::toDto))
                .collect(Collectors.toUnmodifiableList());

        return new CustomerOrderViewDto(customerOrder.getId(), customerOrder.getDate(), contents, customerOrder.getStatus());
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
}
