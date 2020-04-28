package edu.pwr.pizzeria.mock;

import edu.pwr.pizzeria.model.Pizza;
import edu.pwr.pizzeria.model.dto.PizzaDto;
import java.util.Random;

public class RandomPizzaBuilder {

    private static final Random random = new Random();

    private static int generateRandomInt(int upperRange){

        return random.nextInt(upperRange);
    }

    public PizzaDto generateRandomPizza(){

        Pizza randomPizza = new Pizza();

        return PizzaDto.toDto(randomPizza);
    }
}
