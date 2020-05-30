package edu.pwr.pizzeria.service.order.calculator;

import edu.pwr.pizzeria.model.order.CustomerOrder;

public interface PriceCalculator {
    void calculate(CustomerOrder customerOrder);
}
