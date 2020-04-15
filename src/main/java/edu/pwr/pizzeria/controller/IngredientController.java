package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.model.dto.IngredientDto;
import edu.pwr.pizzeria.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = "/{id}")
    public IngredientDto getIngredient(@PathVariable int id){return ingredientService.getIngredient(id);}

    @GetMapping(value = {"", "/"})
    public List<IngredientDto> getAll(){ return ingredientService.getAll();}
}
