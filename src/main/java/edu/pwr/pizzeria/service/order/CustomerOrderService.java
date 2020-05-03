package edu.pwr.pizzeria.service.order;

import edu.pwr.pizzeria.model.order.dto.CustomerOrderDto;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.repository.CustomerOrderRepository;
import edu.pwr.pizzeria.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerOrderService {

    private CustomerOrderRepository customerOrderRepository;
    private PizzaRepository pizzaRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Transactional
    public void createOrder(CustomerOrderDto customerOrderDto) {
        final List<Pizza> pizzaList = customerOrderDto.getPizzas()
                .stream()
                .filter(Objects::nonNull)
                .map(pizzaId -> pizzaRepository.getOne(pizzaId))
                .collect(Collectors.toList());
    }
}
