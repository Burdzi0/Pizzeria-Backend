package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.model.dto.PizzaDto;
import edu.pwr.pizzeria.service.PizzaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/pizza")
public class PizzaController {

    private PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping(value = "/{id}")
    public PizzaDto getPizza(@PathVariable int id) {
        return pizzaService.getPizza(id);
    }

    @GetMapping(value = {"", "/"})
    public List<PizzaDto> getAll() {
        return pizzaService.getAll();
    }
}
