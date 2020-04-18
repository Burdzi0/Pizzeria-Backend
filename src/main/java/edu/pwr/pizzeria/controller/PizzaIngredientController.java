package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.model.dto.PizzaIngredientDto;
import edu.pwr.pizzeria.service.PizzaIngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/pizzaIngredient")
public class PizzaIngredientController {

    private PizzaIngredientService pizzaIngredientService;

    public PizzaIngredientController(PizzaIngredientService pizzaIngredientService) {
        this.pizzaIngredientService = pizzaIngredientService;
    }

    @GetMapping(value = "/{id}")
    public PizzaIngredientDto getPizzaIngredient(@PathVariable int id) {
        return pizzaIngredientService.getPizzaIngredient(id);
    }

    @GetMapping(value = {"", "/"})
    public List<PizzaIngredientDto> getAll() {
        return pizzaIngredientService.getAll();
    }
}
