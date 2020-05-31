package edu.pwr.pizzeria.config;

import edu.pwr.pizzeria.mock.RandomPizzaBuilder;
import edu.pwr.pizzeria.model.pizza.StandardPizza;
import edu.pwr.pizzeria.repository.StandardPizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataStartup implements CommandLineRunner {

    private StandardPizzaRepository standardPizzaRepository;
    private RandomPizzaBuilder randomPizzaBuilder;

    public DataStartup(StandardPizzaRepository standardPizzaRepository, RandomPizzaBuilder randomPizzaBuilder) {
        this.standardPizzaRepository = standardPizzaRepository;
        this.randomPizzaBuilder = randomPizzaBuilder;
    }

    @Override
    public void run(String... args) {

        for (int i = 0; i < 10; i++) {
            StandardPizza randomPizza = randomPizzaBuilder.generateRandomPizza();
            standardPizzaRepository.save(randomPizza);
        }
    }
}
