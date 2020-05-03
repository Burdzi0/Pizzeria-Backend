package edu.pwr.pizzeria.repository;

import edu.pwr.pizzeria.model.order.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
