package edu.pwr.pizzeria.service;

import edu.pwr.pizzeria.model.dto.PizzaDto;
import edu.pwr.pizzeria.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Transactional(readOnly = true)
    public PizzaDto getPizza(int id) {
        return pizzaRepository.findById(id)
                .map(PizzaDto::toDto)
                .orElseThrow(() -> new PizzaNotFoundException("Pizza with id:" + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<PizzaDto> getAll() {
        return pizzaRepository.findAll()
                .stream()
                .map(PizzaDto::toDto)
                .collect(Collectors.toList());
    }
}
