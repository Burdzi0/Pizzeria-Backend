package edu.pwr.pizzeria.repository;

import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.CustomerOrderStatus;
import edu.pwr.pizzeria.model.user.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> getAllByStatusIsIn(Set<CustomerOrderStatus> statuses);
    List<CustomerOrder> getAllByCustomerUser(CustomerUser customerUser);
}
