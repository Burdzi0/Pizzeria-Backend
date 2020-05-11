package edu.pwr.pizzeria.exception;

public class VerificationTokenNotFound extends RuntimeException {
    public VerificationTokenNotFound(String message) {
        super(message);
    }
}
