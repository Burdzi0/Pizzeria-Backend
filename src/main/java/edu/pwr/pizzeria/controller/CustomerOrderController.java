package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.model.order.dto.ChangeStatusRequestDto;
import edu.pwr.pizzeria.model.order.dto.CustomerOrderDto;
import edu.pwr.pizzeria.service.order.CustomerOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(value = "/v1/order")
public class CustomerOrderController {

    private CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Order created successfully"),
            @ApiResponse(code = 400, message = "Suggested status doesn't follow the set order")
    })
    @PostMapping("")
    public void acceptOrder(@RequestBody @Valid CustomerOrderDto customerOrderDto, Principal principal) {
        customerOrderService.createOrder(customerOrderDto, principal.getName());
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "Status changed successfully"),
            @ApiResponse(code = 400, message = "Suggested status doesn't follow the set order")
    })
    @ApiOperation(value = "Advance status", notes = "Advance the given status accordingly to the set order")
    @PostMapping("/advance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void requestOrderStatusChange(@RequestBody @Valid ChangeStatusRequestDto changeStatusRequestDto) {
        customerOrderService.advanceStatus(changeStatusRequestDto.getId(), changeStatusRequestDto.getNewOrderStatus());
    }
}
