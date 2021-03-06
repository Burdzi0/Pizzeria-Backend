package edu.pwr.pizzeria.mock;

import com.github.javafaker.Faker;
import edu.pwr.pizzeria.model.pizza.Ingredient;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;
import edu.pwr.pizzeria.repository.IngredientRepository;
import edu.pwr.pizzeria.repository.PizzaRepository;
import edu.pwr.pizzeria.service.order.calculator.PriceCalculator;
import edu.pwr.pizzeria.service.order.calculator.StandardPriceCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.max;

@Service
public class RandomPizzaBuilder {

    private final Random random = new Random();
    private final Faker faker = new Faker();

    private final int MAX_INGREDIENT_QTY = 4;
    private int[] testDiameters = new int[]{30, 45};

    private IngredientRepository ingredientRepository;
    private PizzaRepository pizzaRepository;
    private PriceCalculator priceCalculator;

    public RandomPizzaBuilder(IngredientRepository ingredientRepository, PizzaRepository pizzaRepository, PriceCalculator priceCalculator) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
        this.priceCalculator = priceCalculator;
    }

    private int generateRandomInt(int upperRange) {
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

    @Transactional
    public Pizza generateRandomPizza() {
        final String typeName = generateRandomTypeName();
        final List<PizzaIngredient> ingredients = generateRandomIngredients();
        final int diameter = generateRandomDiameter();

        Pizza randomPizza = new Pizza(typeName, ingredients, diameter);
        randomPizza.setPrice20(priceCalculator.calculate20(randomPizza));
        randomPizza.setPrice30(priceCalculator.calculate30(randomPizza));

        pizzaRepository.save(randomPizza);
        return randomPizza;
    }

    private String generateRandomTypeName() {
        return faker.address().cityName();
    }

    private List<PizzaIngredient> generateRandomIngredients() {
        final List<Ingredient> testIngredientsCopy = ingredientRepository.findAll();
        int ingredientsNum = generateRandomInt(testIngredientsCopy.size());
        ingredientsNum = max(ingredientsNum, 2);

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

    public void generateRandomPizzas(int pizzas) {
        initializeTestIngredients();

        for (int i = 0; i < pizzas; i++) {
            generateRandomPizza();
        }
    }
}
