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

    public DataStartup(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void run(String... args) {
        Pizza pizza = new Pizza();
        pizza.setCrust(PizzaCrust.THICK);
        pizza.setDiameter(30);
        pizza.setPizzaIngredients(List.of(new PizzaIngredient(new Ingredient("Cheese", BigDecimal.valueOf(4.5d), true), 1)));
        pizza.setTypeName("Test");
        pizzaRepository.save(pizza);

        Pizza pizza1 = new Pizza();
        pizza1.setCrust(PizzaCrust.THICK);
        pizza1.setDiameter(45);
        pizza1.setPizzaIngredients(List.of(new PizzaIngredient(new Ingredient("Ham", BigDecimal.valueOf(4.5d), false), 1)));
        pizza1.setTypeName("Test1");
        pizzaRepository.save(pizza1);

        RandomPizzaBuilder randomPizzaBuilder = new RandomPizzaBuilder();
        randomPizzaBuilder.initializeTestIngredients();

        Pizza pizza2 = randomPizzaBuilder.generateRandomPizza();
        pizzaRepository.save(pizza2);
    }
}
