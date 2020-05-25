package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.model.order.dto.ChangeStatusRequestDto;
import edu.pwr.pizzeria.service.order.CustomerOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/order")
public class CustomerOrderController {

    private CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "Status changed successfully"),
            @ApiResponse(code = 400, message = "Suggested status doesn't follow the set order")
    })
    @ApiOperation(value = "Advance status", notes = "Advance the given status accordingly to the set order")
    @PostMapping("/advance_order")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public void requestOrderStatusChange(@RequestParam ChangeStatusRequestDto changeStatusRequestDto){
        customerOrderService.advanceStatus(changeStatusRequestDto.getId(), changeStatusRequestDto.getNewOrderStatus());
    }
}
