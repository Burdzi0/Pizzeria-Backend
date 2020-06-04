package edu.pwr.pizzeria.model.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerOrderStatus {

    COOK_AWAITING("OCZEKUJĄCE", 1),
    COOK_IN_PROGRESS("W TRAKCIE REALIZACJI", 2),
    DELIVERY_AWAITING("OCZEKUJĄCE", 3),
    DELIVERY_IN_PROGRESS("W DRODZE", 4),
    DELIVERY_READY("DOSTARCZONE", 5);

    private String state;
    private int ordinalNumber;

    CustomerOrderStatus(String state, int ordinalNumber) {
        this.state = state;
        this.ordinalNumber = ordinalNumber;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    @JsonValue
    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "CustomerOrderStatus{" +
                "state='" + state + '\'' +
                ", ordinalNumber=" + ordinalNumber +
                '}';
    }
}
