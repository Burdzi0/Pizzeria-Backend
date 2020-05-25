package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;

import java.util.Objects;

public class ChangeStatusRequestDto {

    private final Long id;
    private final CustomerOrderStatus newOrderStatus;

    @JsonCreator
    public ChangeStatusRequestDto(@JsonProperty("id") Long id,
                            @JsonProperty("newOrderStatus") CustomerOrderStatus newOrderStatus){
        this.id = id;
        this.newOrderStatus = newOrderStatus;
    }

    public Long getId() {
        return id;
    }

    public CustomerOrderStatus getNewOrderStatus() {
        return newOrderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeStatusRequestDto that = (ChangeStatusRequestDto) o;
        return id.equals(that.id) &&
                newOrderStatus == that.newOrderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, newOrderStatus);
    }

    @Override
    public String toString() {
        return "ChangeStatusRequestDto{" +
                "id=" + id +
                ", newOrderStatus=" + newOrderStatus +
                '}';
    }
}
