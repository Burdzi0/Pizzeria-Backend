package edu.pwr.pizzeria.service.ingredient;

public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException(String s) {
        super(s);
    }
}
