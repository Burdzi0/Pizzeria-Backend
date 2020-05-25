package edu.pwr.pizzeria.service.order;

import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;
import edu.pwr.pizzeria.model.order.dto.CustomerOrderDto;
import edu.pwr.pizzeria.repository.CustomerOrderRepository;
import edu.pwr.pizzeria.service.pizza.PizzaNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

import static edu.pwr.pizzeria.model.order.CustomerOrderStatus.*;

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

    @ApiResponses({
            @ApiResponse(code = 201, message = "Status changed successfully"),
            @ApiResponse(code = 400, message = "Suggested status doesn't follow the set order")
    })
    @ApiOperation(value = "Advance status", notes = "Advance the given status accordingly to the set order")
    @PostMapping("/advance_order")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void advanceStatus(@RequestParam Long id, @RequestParam CustomerOrderStatus newStatus) {

        CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new CustomerOrderException("CustomerOrder with id:" + id + " not found"));

        if(ifCorrectOrder(customerOrder.getStatus(), newStatus)) customerOrder.setStatus(newStatus);

        customerOrderRepository.save(customerOrder);
    }

    private boolean ifCorrectOrder(CustomerOrderStatus currentStatus, CustomerOrderStatus newStatus){

        if(currentStatus.getOrdinalNumber() == newStatus.getOrdinalNumber() - 1) return true;
        else return false;
    }
}
