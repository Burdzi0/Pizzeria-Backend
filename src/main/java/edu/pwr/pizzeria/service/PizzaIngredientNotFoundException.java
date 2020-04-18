package edu.pwr.pizzeria.service;

public class PizzaIngredientNotFoundException extends RuntimeException {

    public PizzaIngredientNotFoundException(String s) {
        super(s);
    }
}
