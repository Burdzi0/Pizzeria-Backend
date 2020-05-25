package edu.pwr.pizzeria.service.order;

import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;
import edu.pwr.pizzeria.repository.CustomerOrderRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class CustomerOrderService {

    private CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "Status changed successfully"),
            @ApiResponse(code = 400, message = "Suggested status doesn't follow the set order")
    })
    @ApiOperation(value = "Advance status", notes = "Advance the given status accordingly to the set order")
    @PostMapping("/advance_order")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public void advanceStatus(@RequestParam Long id, @RequestParam CustomerOrderStatus newStatus) {

        CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new CustomerOrderNotFoundException("CustomerOrder with id:" + id + " not found"));

        if (ifCorrectOrder(customerOrder.getStatus(), newStatus)) customerOrder.setStatus(newStatus);

        customerOrderRepository.save(customerOrder);
    }

    private boolean ifCorrectOrder(CustomerOrderStatus currentStatus, CustomerOrderStatus newStatus) {

        return currentStatus.getOrdinalNumber() == newStatus.getOrdinalNumber() - 1;
    }
}
