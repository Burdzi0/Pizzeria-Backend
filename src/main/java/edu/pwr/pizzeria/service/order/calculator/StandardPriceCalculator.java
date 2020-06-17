package edu.pwr.pizzeria.service.order.calculator;

import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.OrderedIngredient;
import edu.pwr.pizzeria.model.order.OrderedPizza;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static edu.pwr.pizzeria.model.pizza.Pizza.*;

@Service
public class StandardPriceCalculator implements PriceCalculator {

    @Override
    public BigDecimal calculate(CustomerOrder customerOrder) {
        double sum = 0.0;

        for (OrderedPizza orderedPizza : customerOrder.getPizzas()) {
            sum = orderedPizza.getPrice().doubleValue();
        }

        for (CustomPizza orderedPizza : customerOrder.getCustoms()) {
            sum += calculatePizza(orderedPizza).doubleValue();
        }

        return BigDecimal.valueOf(sum);
    }

    @Override
    public BigDecimal calculatePizza(CustomPizza customPizza) {
        double sum = 0.0;

        switch (customPizza.getCrust()) {
            case GRUBE:
                sum += THICK_CRUST_PRICE;
            case CIENKIE:
                sum += THIN_CRUST_PRICE;
        }

        for (OrderedIngredient ingredient : customPizza.getPizzaIngredients()) {
            sum += ingredient.getPrice().doubleValue() * ingredient.getQuantity();
        }

        if (customPizza.getDiameter() == BIG_DIAMETER) {
            sum *= 1.5;
        }

        return BigDecimal.valueOf(sum);
    }

    @Override
    public BigDecimal calculate20(Pizza randomPizza) {
        double sum = 0.0;

        switch (randomPizza.getCrust()) {
            case GRUBE:
                sum += THICK_CRUST_PRICE;
            case CIENKIE:
                sum += THIN_CRUST_PRICE;
        }

        for (PizzaIngredient ingredient : randomPizza.getIngredients()) {
            sum += ingredient.getIngredient().getPrice().doubleValue() * ingredient.getQuantity();
        }

        return BigDecimal.valueOf(sum);
    }

    @Override
    public BigDecimal calculate30(Pizza randomPizza) {
        double sum = 0.0;

        switch (randomPizza.getCrust()) {
            case GRUBE:
                sum += THICK_CRUST_PRICE;
            case CIENKIE:
                sum += THIN_CRUST_PRICE;
        }

        for (PizzaIngredient ingredient : randomPizza.getIngredients()) {
            sum += ingredient.getIngredient().getPrice().doubleValue() * ingredient.getQuantity();
        }
        sum *= 1.5;

        return BigDecimal.valueOf(sum);
    }
}
