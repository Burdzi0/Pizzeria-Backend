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
        final BigDecimal standardsPrice = customerOrder.getPizzas()
                .stream()
                .map(OrderedPizza::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        final BigDecimal customsPrice = customerOrder.getCustoms()
                .stream()
                .map(this::calculatePizza)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return standardsPrice.add(customsPrice);
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

        if (customPizza.getDiameter() == BIG_DIAMETER) {
            sum *= 1.5;
        }

        return BigDecimal.valueOf(sum);
    }
}
