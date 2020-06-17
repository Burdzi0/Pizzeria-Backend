package edu.pwr.pizzeria.service.pizza;

public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException(String s) {
        super(s);
    }
}
