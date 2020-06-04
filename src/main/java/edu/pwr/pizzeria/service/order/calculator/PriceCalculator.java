package edu.pwr.pizzeria.service.order.calculator;

import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.CustomerOrder;

import java.math.BigDecimal;

public interface PriceCalculator {
    BigDecimal calculate(CustomerOrder customerOrder);
    BigDecimal calculatePizza(CustomPizza customPizza);
}
