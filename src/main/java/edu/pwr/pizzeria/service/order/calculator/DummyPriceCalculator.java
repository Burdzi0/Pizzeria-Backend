package edu.pwr.pizzeria.service.order.calculator;

import edu.pwr.pizzeria.model.order.CustomerOrder;
import org.springframework.stereotype.Service;

@Service
public class DummyPriceCalculator implements PriceCalculator {

    @Override
    public void calculate(CustomerOrder customerOrder) {
        // TODO customerOrder.setTotal(X);
    }
}
