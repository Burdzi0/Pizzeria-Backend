package edu.pwr.pizzeria.repository;

import edu.pwr.pizzeria.model.order.OrderedPizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedPizzaRepository extends JpaRepository<OrderedPizza, Long> {
}
