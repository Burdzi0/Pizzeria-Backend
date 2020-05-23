package edu.pwr.pizzeria.service.order;

import edu.pwr.pizzeria.model.order.CustomerOrderDto;
import edu.pwr.pizzeria.model.pizza.dto.PizzaDto;
import edu.pwr.pizzeria.repository.CustomerOrderRepository;
import edu.pwr.pizzeria.service.pizza.PizzaNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerOrderService {

    private CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Transactional(readOnly = true)
    public CustomerOrderDto getPizza(Long id) {
        return customerOrderRepository.findById(id)
                .map(CustomerOrderDto::toDto)
                .orElseThrow(() -> new PizzaNotFoundException("Pizza with id:" + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<CustomerOrderDto> getAll() {
        return customerOrderRepository.findAll()
                .stream()
                .map(CustomerOrderDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void changeStatus(){

    }
}
