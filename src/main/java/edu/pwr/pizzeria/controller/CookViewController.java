package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.service.order.CustomerOrderService;
import edu.pwr.pizzeria.service.order.dto.CustomerOrderViewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/view")
public class CookViewController {

    private CustomerOrderService service;

    public CookViewController(CustomerOrderService service) {
        this.service = service;
    }

    @GetMapping("/cook")
    public List<CustomerOrderViewDto> getAllForCook() {
        return service.getOrdersForCook();
    }

    @GetMapping("/delivery")
    public List<CustomerOrderViewDto> getAllForDelivery() {
        return service.getOrdersForDeliver();
    }
}
