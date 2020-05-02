package edu.pwr.pizzeria.mock;

import com.github.javafaker.Faker;
import edu.pwr.pizzeria.model.Ingredient;
import edu.pwr.pizzeria.model.Pizza;
import edu.pwr.pizzeria.model.PizzaIngredient;
import edu.pwr.pizzeria.model.dto.PizzaDto;
import edu.pwr.pizzeria.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RandomPizzaBuilder {

    private static final Random random = new Random();
    private final Faker faker = new Faker();
    private final int MAX_INGREDIENT_QTY = 4;
    private int[] testDiameters = new int[]{30, 45};
    private IngredientRepository ingredientRepository;

    public RandomPizzaBuilder(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void initializeTestIngredients() {

        List<Ingredient> testIngredients = new ArrayList<>();

        testIngredients.add(new Ingredient("ham", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("gouda cheese", BigDecimal.valueOf(3.0d), true));
        testIngredients.add(new Ingredient("mushrooms", BigDecimal.valueOf(2.0d), true));
        testIngredients.add(new Ingredient("mozzarella", BigDecimal.valueOf(3.0d), true));
        testIngredients.add(new Ingredient("pepperoni", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("pepper", BigDecimal.valueOf(2.0d), false));
        testIngredients.add(new Ingredient("corn", BigDecimal.valueOf(2.0d), true));
        testIngredients.add(new Ingredient("eggplant", BigDecimal.valueOf(2.0d), false));
        testIngredients.add(new Ingredient("tofu", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("spinach", BigDecimal.valueOf(2.0d), false));
        testIngredients.add(new Ingredient("shrimps", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("chicken", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("olives", BigDecimal.valueOf(2.0d), false));
        testIngredients.add(new Ingredient("pineapple", BigDecimal.valueOf(3.0d), false));

        ingredientRepository.saveAll(testIngredients);
    }

    private static int generateRandomInt(int upperRange) {

        return random.nextInt(upperRange);
    }

    public Pizza generateRandomPizza() {

        String typeName = generateRandomTypeName();
        List<PizzaIngredient> ingredients = generateRandomIngredients();
        int diameter = generateRandomDiameter();

        return new Pizza(typeName, ingredients, diameter);
    }

    private String generateRandomTypeName() {

        return faker.address().cityName();
    }

    private List<PizzaIngredient> generateRandomIngredients() {

        List<Ingredient> testIngredientsCopy = ingredientRepository.findAll();
        int ingredientsNum = generateRandomInt(testIngredientsCopy.size());
        int ingredientIndex;
        int ingredientQty;

        List<PizzaIngredient> randomIngredients = new ArrayList<>();
        PizzaIngredient randomIngredient;

        for (int i = 0; i < ingredientsNum; i++) {
            ingredientIndex = generateRandomInt(testIngredientsCopy.size() - 1);
            ingredientQty = generateRandomInt(MAX_INGREDIENT_QTY);

            randomIngredient = new PizzaIngredient(testIngredientsCopy.get(ingredientIndex), ingredientQty);
            randomIngredients.add(randomIngredient);

            testIngredientsCopy.remove(ingredientIndex);
        }

        return randomIngredients;
    }

    private int generateRandomDiameter() {
        return testDiameters[generateRandomInt(1)];
    }
}
