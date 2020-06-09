package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;
import edu.pwr.pizzeria.model.user.AddressDto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerOrderFullDto {

    private Long id;
    private AddressDto address;
    private List<OrderedPizzaDto> orderedPizzas;
    private BigDecimal total;
    private Instant date;
    private CustomerOrderStatus status;

    @JsonCreator
    public CustomerOrderFullDto(@JsonProperty("id") Long id,
                                @JsonProperty("address") AddressDto address,
                                @JsonProperty("orderedPizzas") List<OrderedPizzaDto> orderedPizzas,
                                @JsonProperty("total") BigDecimal total,
                                @JsonProperty("date") Instant date,
                                @JsonProperty("status") CustomerOrderStatus status) {
        this.id = id;
        this.address = address;
        this.orderedPizzas = orderedPizzas;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public static CustomerOrderFullDto toDto(CustomerOrder customerOrder) {
        final var customs = customerOrder.getCustoms()
                .stream()
                .map(OrderedPizzaDto::toDto);

        final var standards = customerOrder.getPizzas()
                .stream()
                .map(OrderedPizzaDto::toDto);

        final var address = AddressDto.toDto(customerOrder.getAddress());
        return new CustomerOrderFullDto(customerOrder.getId(),
                address,
                Stream.concat(customs, standards).collect(Collectors.toUnmodifiableList()),
                customerOrder.getTotal(),
                customerOrder.getDate(),
                customerOrder.getStatus());
    }

    public Long getId() { return id; }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<OrderedPizzaDto> getOrderedPizzas() {
        return orderedPizzas;
    }

    public void setOrderedPizzas(List<OrderedPizzaDto> orderedPizzas) {
        this.orderedPizzas = orderedPizzas;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public CustomerOrderStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerOrderStatus status) {
        this.status = status;
    }
}
