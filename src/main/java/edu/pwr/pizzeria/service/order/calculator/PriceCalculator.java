package edu.pwr.pizzeria.service.order.calculator;

import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.pizza.Pizza;

import java.math.BigDecimal;

public interface PriceCalculator {
    BigDecimal calculate(CustomerOrder customerOrder);
    BigDecimal calculatePizza(CustomPizza customPizza);

    BigDecimal calculate20(Pizza randomPizza);

    BigDecimal calculate30(Pizza randomPizza);
}
