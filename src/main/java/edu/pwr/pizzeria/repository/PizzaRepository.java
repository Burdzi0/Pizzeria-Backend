package edu.pwr.pizzeria.repository;

import edu.pwr.pizzeria.model.pizza.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
