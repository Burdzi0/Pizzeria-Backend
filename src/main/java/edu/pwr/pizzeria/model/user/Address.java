package edu.pwr.pizzeria.model.user;

import java.util.Objects;

public class Address {

    private String street;
    private int number;
    private String phoneNumber;

    public Address(String street, int number, String phoneNumber) {
        this.street = street;
        this.number = number;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number &&
                street.equals(address.street) &&
                Objects.equals(phoneNumber, address.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, phoneNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
