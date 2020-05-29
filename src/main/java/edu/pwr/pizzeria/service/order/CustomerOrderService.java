package edu.pwr.pizzeria.service.order;

import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;
import edu.pwr.pizzeria.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerOrderService {

    private CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Transactional
    public void advanceStatus(Long id, CustomerOrderStatus newStatus) {

        final CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new CustomerOrderNotFoundException("CustomerOrder with id:" + id + " not found"));

        if (ifCorrectOrder(customerOrder.getStatus(), newStatus)) customerOrder.setStatus(newStatus);

        customerOrderRepository.save(customerOrder);
    }

    private boolean ifCorrectOrder(CustomerOrderStatus currentStatus, CustomerOrderStatus newStatus) {

        return currentStatus.getOrdinalNumber() == newStatus.getOrdinalNumber() - 1;
    }
}
