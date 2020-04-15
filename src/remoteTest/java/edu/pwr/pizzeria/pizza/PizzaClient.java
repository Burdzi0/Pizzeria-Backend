package edu.pwr.pizzeria.pizza;

import edu.pwr.pizzeria.model.dto.PizzaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "pizzaClient", url = "${herokuUrl}")
public interface PizzaClient {

    @GetMapping("/v1/pizza")
    List<PizzaDto> getAll();

    @GetMapping("/v1/pizza/{id}")
    PizzaDto getPizza(@PathVariable int id);
}
