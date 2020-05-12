package edu.pwr.pizzeria.service.order;

import edu.pwr.pizzeria.model.order.dto.CookViewOrderDto;
import edu.pwr.pizzeria.model.order.dto.DeliveryViewOrderDto;
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
    public void createOrder(CookViewOrderDto cookViewOrderDto) {
//        final List<Pizza> pizzaList = cookViewOrderDto.getPizzas()
//                .stream()
//                .filter(Objects::nonNull)
//                .map(pizzaId -> pizzaRepository.getOne(pizzaId))
//                .collect(Collectors.toList());
    }
}
