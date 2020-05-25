package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.service.order.CustomerOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/order")
public class CustomerOrderController {

    private CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }


}
