package edu.pwr.pizzeria.service;

import edu.pwr.pizzeria.model.dto.IngredientDto;
import edu.pwr.pizzeria.repository.IngredientRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional(readOnly = true)
    public IngredientDto getIngredient(int id) {
        return ingredientRepository.findById(id)
                .map(IngredientDto::toDto)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient with id: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<IngredientDto> getAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(IngredientDto::toDto)
                .collect(Collectors.toList());
    }
}
