package edu.pwr.pizzeria.service.order;

public class WrongOrderStatusException extends RuntimeException{

    public WrongOrderStatusException(String s) {
        super(s);
    }
}
