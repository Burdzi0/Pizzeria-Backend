package edu.pwr.pizzeria.config;

import edu.pwr.pizzeria.mock.RandomPizzaBuilder;
import edu.pwr.pizzeria.model.Ingredient;
import edu.pwr.pizzeria.model.Pizza;
import edu.pwr.pizzeria.model.PizzaCrust;
import edu.pwr.pizzeria.model.PizzaIngredient;
import edu.pwr.pizzeria.repository.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataStartup implements CommandLineRunner {

    private PizzaRepository pizzaRepository;
    private RandomPizzaBuilder randomPizzaBuilder;

    public DataStartup(PizzaRepository pizzaRepository, RandomPizzaBuilder randomPizzaBuilder) {
        this.pizzaRepository = pizzaRepository;
        this.randomPizzaBuilder = randomPizzaBuilder;
    }

    @Override
    public void run(String... args) {

        randomPizzaBuilder.initializeTestIngredients();

        for(int i = 0; i < 10; i++){
            Pizza randomPizza = randomPizzaBuilder.generateRandomPizza();
            pizzaRepository.save(randomPizza);
        }
    }
}
