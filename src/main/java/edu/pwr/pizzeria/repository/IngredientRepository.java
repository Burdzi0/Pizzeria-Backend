package edu.pwr.pizzeria.repository;

import edu.pwr.pizzeria.model.pizza.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
