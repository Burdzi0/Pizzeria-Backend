package edu.pwr.pizzeria.service;

public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException(String s) {
        super(s);
    }
}
