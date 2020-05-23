package edu.pwr.pizzeria.model.order;

public enum CustomerOrderStatus {

    COOK_AWAITING("OCZEKUJĄCE"), COOK_IN_PROGRESS("W TRAKCIE REALIZACJI"), COOK_READY("GOTOWE"),
    DELIVERY_AWAITNING("OCZEKUJĄCE"), DELIVERY_IN_PROGRESS("W DRODZE"), DELIVERY_READY("DOSTARCZONE");
    private String state;

    CustomerOrderStatus(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
}
