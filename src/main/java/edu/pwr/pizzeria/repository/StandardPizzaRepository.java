package edu.pwr.pizzeria.repository;

import edu.pwr.pizzeria.model.pizza.StandardPizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardPizzaRepository extends JpaRepository<StandardPizza, Integer> {
}
