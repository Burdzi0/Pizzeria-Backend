package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.model.order.dto.CustomerOrderDto;
import edu.pwr.pizzeria.service.order.CustomerOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/order")
public class CustomerOrderController {

    private CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @GetMapping(value = "/{id}")
    public CustomerOrderDto getPizza(@PathVariable Long id) {
        return customerOrderService.getPizza(id);
    }

    @GetMapping
    public List<CustomerOrderDto> getAll() {
        return customerOrderService.getAll();
    }
}
