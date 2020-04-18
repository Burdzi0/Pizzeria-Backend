package edu.pwr.pizzeria.service;

import edu.pwr.pizzeria.model.dto.PizzaIngredientDto;
import edu.pwr.pizzeria.repository.PizzaIngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaIngredientService {

    private PizzaIngredientRepository pizzaIngredientRepository;

    public PizzaIngredientService(PizzaIngredientRepository pizzaIngredientRepository) {
        this.pizzaIngredientRepository = pizzaIngredientRepository;
    }

    @Transactional(readOnly = true)
    public PizzaIngredientDto getPizzaIngredient(int id){
        return  pizzaIngredientRepository.findById(id)
                .map(PizzaIngredientDto::toDto)
                .orElseThrow(() -> new PizzaIngredientNotFoundException("Pizza ingredient with id: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<PizzaIngredientDto> getAll(){
        return pizzaIngredientRepository.findAll()
                .stream()
                .map(PizzaIngredientDto::toDto)
                .collect(Collectors.toList());
    }
}
