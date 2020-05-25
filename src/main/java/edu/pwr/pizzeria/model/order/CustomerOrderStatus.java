package edu.pwr.pizzeria.model.order;

public enum CustomerOrderStatus {

    COOK_AWAITING("OCZEKUJĄCE", 1), COOK_IN_PROGRESS("W TRAKCIE REALIZACJI", 2),
    COOK_READY("GOTOWE", 3), DELIVERY_AWAITING("OCZEKUJĄCE", 4),
    DELIVERY_IN_PROGRESS("W DRODZE", 5), DELIVERY_READY("DOSTARCZONE", 6);

    private String state;
    private int ordinalNumber;

    CustomerOrderStatus(String state, int ordinalNumber) {
        this.state = state;
        this.ordinalNumber = ordinalNumber;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    @Override
    public String toString() {
        return "CustomerOrderStatus{" +
                "state='" + state + '\'' +
                ", ordinalNumber=" + ordinalNumber +
                '}';
    }
}
