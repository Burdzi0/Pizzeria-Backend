package edu.pwr.pizzeria.service.order.calculator;

import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StandardPriceCalculator implements PriceCalculator {

    @Override
    public BigDecimal calculate(CustomerOrder customerOrder) {
        // TODO
        return null;
    }

    @Override
    public BigDecimal calculatePizza(CustomPizza customPizza) {
        // TODO
        return null;
    }
}
