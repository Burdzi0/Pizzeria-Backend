package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.model.dto.IngredientDto;
import edu.pwr.pizzeria.service.ingredient.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = "/{id}")
    public IngredientDto getIngredient(@PathVariable int id){
        return ingredientService.getIngredient(id);}

    @GetMapping
    public List<IngredientDto> getAll(){ return ingredientService.getAll();}
}
