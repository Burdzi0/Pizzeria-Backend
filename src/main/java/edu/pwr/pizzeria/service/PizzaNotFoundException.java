package edu.pwr.pizzeria.service;

public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException(String s) {
        super(s);
    }
}
