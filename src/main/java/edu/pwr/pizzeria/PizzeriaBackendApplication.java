package edu.pwr.pizzeria;

import edu.pwr.pizzeria.mock.RandomPizzaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzeriaBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(PizzeriaBackendApplication.class, args);
        RandomPizzaBuilder rpb = new RandomPizzaBuilder();
        rpb.initializeTestIngredients();
        rpb.generateRandomPizza();
    }

}
