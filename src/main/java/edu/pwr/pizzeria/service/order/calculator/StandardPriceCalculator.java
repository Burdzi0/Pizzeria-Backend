package edu.pwr.pizzeria.service.order.calculator;

import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.OrderedIngredient;
import edu.pwr.pizzeria.model.order.OrderedPizza;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static edu.pwr.pizzeria.model.pizza.Pizza.*;

@Service
public class StandardPriceCalculator implements PriceCalculator {

    @Override
    public BigDecimal calculate(CustomerOrder customerOrder) {

        double sum = 0.0;

        for (OrderedPizza pizza : customerOrder.getPizzas()) {
            sum += pizza.getPrice().doubleValue();
        }

        for (CustomPizza customPizza : customerOrder.getCustoms()) {
            sum += customPizza.getPrice().doubleValue();
        }

        return BigDecimal.valueOf(sum);
    }

    @Override
    public BigDecimal calculatePizza(CustomPizza customPizza) {

        double sum = 0.0;

        switch(customPizza.getCrust()){
            case GRUBE:
                sum += THICK_CRUST_PRICE;
            case CIENKIE:
                sum += THIN_CRUST_PRICE;
        }

        for (OrderedIngredient ingredient : customPizza.getPizzaIngredients()) {
            sum += ingredient.getPrice().doubleValue() * ingredient.getQuantity();
        }

        switch (customPizza.getDiameter()){
            case BIG_DIAMETER:
                sum *= 1.5;
        }

        return BigDecimal.valueOf(sum);
    }
}
