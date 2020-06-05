package edu.pwr.pizzeria.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {

    private String street;
    private String number;
    private String phoneNumber;
    private String email;

    @JsonCreator
    public AddressDto(@JsonProperty("street") String street,
                      @JsonProperty("number") String number,
                      @JsonProperty("phone") String phoneNumber,
                      @JsonProperty("email") String email) {
        this.street = street;
        this.number = number;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static AddressDto toDto(Address address) {
        return new AddressDto(address.getStreet(), address.getNumber(), address.getPhone(), address.getEmail());
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
