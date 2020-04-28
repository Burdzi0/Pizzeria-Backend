package edu.pwr.pizzeria.mock;

import edu.pwr.pizzeria.model.Ingredient;
import edu.pwr.pizzeria.model.Pizza;
import edu.pwr.pizzeria.model.PizzaIngredient;
import edu.pwr.pizzeria.model.dto.PizzaDto;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPizzaBuilder {

    private static final Random random = new Random();
    private final int TYPE_NAME_LENGTH = 25;
    private final int MAX_INGREDIENT_QTY = 4;
    private List<Ingredient> testIngredients;
    private int[] testDiameters = new int[]{30, 45};

    public RandomPizzaBuilder() {
        testIngredients = new ArrayList<>();
    }

    public void initializeTestIngredients(){

        testIngredients.add(new Ingredient("ham", new BigDecimal(4), false));
        testIngredients.add(new Ingredient("gouda cheese", new BigDecimal(3), true));
        testIngredients.add(new Ingredient("mushrooms", new BigDecimal(2), true));
        testIngredients.add(new Ingredient("mozzarella", new BigDecimal(3), true));
        testIngredients.add(new Ingredient("pepperoni", new BigDecimal(4), false));
        testIngredients.add(new Ingredient("pepper", new BigDecimal(2), false));
        testIngredients.add(new Ingredient("corn", new BigDecimal(2), true));
        testIngredients.add(new Ingredient("eggplant", new BigDecimal(2), false));
        testIngredients.add(new Ingredient("tofu", new BigDecimal(4), false));
        testIngredients.add(new Ingredient("spinach", new BigDecimal(2), false));
        testIngredients.add(new Ingredient("shrimps", new BigDecimal(4), false));
        testIngredients.add(new Ingredient("chicken", new BigDecimal(4), false));
        testIngredients.add(new Ingredient("olives", new BigDecimal(2), false));
        testIngredients.add(new Ingredient("pineapple", new BigDecimal(3), false));
    }

    private static int generateRandomInt(int upperRange){

        return random.nextInt(upperRange);
    }

    public PizzaDto generateRandomPizza(){

        String typeName = generateRandomTypeName();
        List<PizzaIngredient> ingredients = generateRandomIngredients();
        int diameter = generateRandomDiameter();

        Pizza randomPizza = new Pizza(typeName, ingredients, diameter);

        return PizzaDto.toDto(randomPizza);
    }

    private String generateRandomTypeName() {

        byte[] array = new byte[TYPE_NAME_LENGTH];
        random.nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }

    private List<PizzaIngredient> generateRandomIngredients() {

        List<Ingredient> testIngredientsCopy = new ArrayList<>(testIngredients);
        int ingredientsNum = generateRandomInt(testIngredientsCopy.size());
        int ingredientIndex;
        int ingredientQty;

        List<PizzaIngredient> randomIngredients = new ArrayList<>();
        PizzaIngredient randomIngredient;

        for(int i = 0; i < ingredientsNum; i++){
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
