package edu.pwr.pizzeria.mock;

import com.github.javafaker.Faker;
import edu.pwr.pizzeria.model.pizza.Ingredient;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;
import edu.pwr.pizzeria.model.pizza.StandardPizza;
import edu.pwr.pizzeria.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.max;

@Service
public class RandomPizzaBuilder {

    private static final Random random = new Random();
    private final Faker faker = new Faker();
    private final int MAX_INGREDIENT_QTY = 4;
    private int[] testDiameters = new int[]{30, 45};
    private IngredientRepository ingredientRepository;

    public RandomPizzaBuilder(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        testIngredients();
    }

    private static int generateRandomInt(int upperRange) {
        return random.nextInt(upperRange);
    }

    public void testIngredients() {
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
        ingredientRepository.flush();
    }

    public StandardPizza generateRandomPizza() {
        final StandardPizza standardPizza = new StandardPizza();

        final String typeName = generateRandomTypeName();
        final List<PizzaIngredient> ingredients = generateRandomIngredients(standardPizza);
        final int diameter = generateRandomDiameter();

        standardPizza.setTypeName(typeName);
        standardPizza.setIngredients(ingredients);
        standardPizza.setDiameter(diameter);
        return standardPizza;
    }

    private String generateRandomTypeName() {
        return faker.address().cityName();
    }

    private List<PizzaIngredient> generateRandomIngredients(StandardPizza standardPizza) {
        int ingredientsNum = generateRandomInt(6);
        ingredientsNum = max(ingredientsNum, 2);

        final List<PizzaIngredient> randomIngredients = new ArrayList<>();
        PizzaIngredient randomIngredient;

        for (int i = 0; i < ingredientsNum; i++) {
            int max = (int) ingredientRepository.count();
            var ingredient = ingredientRepository.getOne(random.nextInt(max));

            randomIngredient = new PizzaIngredient(standardPizza, ingredient, randomQty());
            randomIngredients.add(randomIngredient);
        }

        return randomIngredients;
    }

    private int randomQty() {
        return random.nextInt(1) + 1;
    }

    private int generateRandomDiameter() {
        return testDiameters[generateRandomInt(1)];
    }
}
