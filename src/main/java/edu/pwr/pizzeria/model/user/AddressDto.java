package edu.pwr.pizzeria.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {

    private String street;
    private String number;
    private String phone;
    private String email;

    @JsonCreator
    public AddressDto(@JsonProperty("street") String street,
                      @JsonProperty("number") String number,
                      @JsonProperty("phone") String phone,
                      @JsonProperty("email") String email) {
        this.street = street;
        this.number = number;
        this.phone = phone;
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
        return phone;
    }

    public void setPhoneNumber(String phone) {
        this.phone = phone;
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
                ", phoneNumber='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
