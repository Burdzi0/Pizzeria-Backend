package edu.pwr.pizzeria.exception;

import edu.pwr.pizzeria.service.ingredient.IngredientNotFoundException;
import edu.pwr.pizzeria.service.pizza.PizzaNotFoundException;
import edu.pwr.pizzeria.service.pizzaingredient.PizzaIngredientNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ModelExceptions {

    @ResponseBody
    @ExceptionHandler(value = PizzaNotFoundException.class)
    public ResponseEntity<StandardException> pizzaNotFoundException(HttpServletRequest req, PizzaNotFoundException ex) {
        return exception(req, ex, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value = IngredientNotFoundException.class)
    public ResponseEntity<StandardException> ingredientNotFoundException(HttpServletRequest req, IngredientNotFoundException ex) {
        return exception(req, ex, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value = PizzaIngredientNotFoundException.class)
    public ResponseEntity<StandardException> pizzaIngredientNotFoundException(HttpServletRequest req, PizzaIngredientNotFoundException ex) {
        return exception(req, ex, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value = InvalidLoginCredentialsException.class)
    public ResponseEntity<StandardException> invalidCredentials(HttpServletRequest req, InvalidLoginCredentialsException ex) {
        return exception(req, ex, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<StandardException> exception(HttpServletRequest req, RuntimeException ex, HttpStatus status) {
        return new ResponseEntity<>(standardException(ex.getMessage(), req.getServletPath(), status), headers(), status);
    }

    private HttpHeaders headers() {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private StandardException standardException(String msg, String path, HttpStatus status) {
        return new StandardException(msg, path, status);
    }
}
