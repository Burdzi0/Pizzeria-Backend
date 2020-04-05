package edu.pwr.pizzeria.repository;

import model.Ingredient;
import model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
