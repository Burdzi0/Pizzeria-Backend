package edu.pwr.pizzeria.service.order;

public class CustomerOrderNotFoundException extends RuntimeException{

    public CustomerOrderNotFoundException(String s) {
        super(s);
    }
}
