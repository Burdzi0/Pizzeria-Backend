package edu.pwr.pizzeria.service.order.calculator;

import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.CustomerOrder;

public interface PriceCalculator {
    void calculate(CustomerOrder customerOrder);

    void calculatePizza(CustomPizza custom);
}
