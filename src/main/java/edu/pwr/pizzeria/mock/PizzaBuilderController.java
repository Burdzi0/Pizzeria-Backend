package edu.pwr.pizzeria.mock;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/generator")
public class PizzaBuilderController {

    private final RandomPizzaBuilder randomPizzaBuilder;

    public PizzaBuilderController(RandomPizzaBuilder randomPizzaBuilder) {
        this.randomPizzaBuilder = randomPizzaBuilder;
    }

    @PostMapping("/{pizzas}")
    @PreAuthorize("hasRole('ADMIN')")
    public void createStandards(@PathVariable int pizzas) {
        randomPizzaBuilder.generateRandomPizzas(pizzas);
    }
}
