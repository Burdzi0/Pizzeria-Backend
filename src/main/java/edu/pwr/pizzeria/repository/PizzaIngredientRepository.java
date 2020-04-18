package edu.pwr.pizzeria.repository;

import edu.pwr.pizzeria.model.PizzaIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaIngredientRepository extends JpaRepository<PizzaIngredient, Integer> {
}
