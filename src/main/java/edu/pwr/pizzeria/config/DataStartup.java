package edu.pwr.pizzeria.config;

import edu.pwr.pizzeria.model.Ingredient;
import edu.pwr.pizzeria.model.Pizza;
import edu.pwr.pizzeria.model.PizzaCrust;
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
        pizza.setIngredients(List.of(new Ingredient("Cheese", 1, BigDecimal.valueOf(4.5d), true)));
        pizza.setTypeName("Test");
        pizzaRepository.save(pizza);

        Pizza pizza1 = new Pizza();
        pizza1.setCrust(PizzaCrust.THICK);
        pizza1.setDiameter(45);
        pizza1.setIngredients(List.of(new Ingredient("Ham", 1, BigDecimal.valueOf(4.5d), false)));
        pizza1.setTypeName("Test1");
        pizzaRepository.save(pizza1);
    }
}
