package edu.pwr.pizzeria.model.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerOrderStatus {

    COOK_AWAITING("OCZEKUJĄCE NA PRZYGOTOWANIE"),
    COOK_IN_PROGRESS("W TRAKCIE REALIZACJI"),
    DELIVERY_AWAITING("OCZEKUJĄCE NA DOSTAWĘ"),
    DELIVERY_IN_PROGRESS("W DRODZE"),
    DELIVERY_READY("DOSTARCZONE");

    private String state;

    CustomerOrderStatus(String state) {
        this.state = state;
    }

    @JsonValue
    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "CustomerOrderStatus{" +
                "state='" + state + '\'' +
                '}';
    }
}
