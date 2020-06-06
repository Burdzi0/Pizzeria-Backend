package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.model.order.dto.CustomerOrderFullDto;
import edu.pwr.pizzeria.model.user.Address;
import edu.pwr.pizzeria.model.user.AddressDto;
import edu.pwr.pizzeria.model.user.CustomerUser;
import edu.pwr.pizzeria.service.user.CustomerUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class CustomerUserController {

    private CustomerUserService customerUserService;

    public CustomerUserController(CustomerUserService customerUserService) {
        this.customerUserService = customerUserService;
    }

    @GetMapping("/orders")
    public List<CustomerOrderFullDto> orders(Principal principal) {
        return customerUserService.getCustomerOrders(principal.getName());
    }

    @PostMapping("/address")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateAddress(@Valid @RequestBody AddressDto addressDto, Principal principal) {
        customerUserService.updateAddress(principal.getName(), addressDto);
    }

    @GetMapping("/userAddress")
    public Address getAddress(@Valid @RequestBody Principal principal){
        return ((CustomerUser) principal).getAddress();
    }
}
