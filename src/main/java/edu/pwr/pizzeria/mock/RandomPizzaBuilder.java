package edu.pwr.pizzeria.mock;

import com.github.javafaker.Faker;
import edu.pwr.pizzeria.model.pizza.Ingredient;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;
import edu.pwr.pizzeria.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RandomPizzaBuilder {

    private static final Random random = new Random();
    private final Faker faker = new Faker();
    private final int MAX_INGREDIENT_QTY = 4;
    private final double MAX_PRICE = 60.0;
    private int[] testDiameters = new int[]{30, 45};
    private IngredientRepository ingredientRepository;

    public RandomPizzaBuilder(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    private static int generateRandomInt(int upperRange) {
        return random.nextInt(upperRange);
    }

    public void initializeTestIngredients() {
        final List<Ingredient> testIngredients = new ArrayList<>();

        testIngredients.add(new Ingredient("szynka", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("ser żółty", BigDecimal.valueOf(3.0d), true));
        testIngredients.add(new Ingredient("pieczarki", BigDecimal.valueOf(2.0d), true));
        testIngredients.add(new Ingredient("mozzarella", BigDecimal.valueOf(3.0d), true));
        testIngredients.add(new Ingredient("pepperoni", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("papryka", BigDecimal.valueOf(2.0d), false));
        testIngredients.add(new Ingredient("kukurydza", BigDecimal.valueOf(2.0d), true));
        testIngredients.add(new Ingredient("bakłażan", BigDecimal.valueOf(2.0d), false));
        testIngredients.add(new Ingredient("tofu", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("szpinak", BigDecimal.valueOf(2.0d), false));
        testIngredients.add(new Ingredient("krewetki", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("kurczak", BigDecimal.valueOf(4.0d), false));
        testIngredients.add(new Ingredient("oliwki", BigDecimal.valueOf(2.0d), false));
        testIngredients.add(new Ingredient("ananas", BigDecimal.valueOf(3.0d), false));

        ingredientRepository.saveAll(testIngredients);
    }

    public Pizza generateRandomPizza() {
        final String typeName = generateRandomTypeName();
        final List<PizzaIngredient> ingredients = generateRandomIngredients();
        final int diameter = generateRandomDiameter();

        Pizza randomPizza = new Pizza(typeName, ingredients, diameter);
        randomPizza.computePrice();

        return randomPizza;
    }

    private BigDecimal generateRandomPrice() {

            return BigDecimal.valueOf(random.nextDouble());
    }

    private String generateRandomTypeName() {
        return faker.address().cityName();
    }

    private List<PizzaIngredient> generateRandomIngredients() {
        final List<Ingredient> testIngredientsCopy = ingredientRepository.findAll();
        final int ingredientsNum = generateRandomInt(testIngredientsCopy.size());

        int ingredientIndex;
        int ingredientQty;

        final List<PizzaIngredient> randomIngredients = new ArrayList<>();
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
