package edu.pwr.pizzeria.service.pizza;

import edu.pwr.pizzeria.model.pizza.dto.PizzaDto;
import edu.pwr.pizzeria.repository.StandardPizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private StandardPizzaRepository standardPizzaRepository;

    public PizzaService(StandardPizzaRepository standardPizzaRepository) {
        this.standardPizzaRepository = standardPizzaRepository;
    }

    @Transactional(readOnly = true)
    public PizzaDto getPizza(int id) {
        return standardPizzaRepository.findById(id)
                .map(PizzaDto::toDto)
                .orElseThrow(() -> new PizzaNotFoundException("Pizza with id:" + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<PizzaDto> getAll() {
        return standardPizzaRepository.findAll()
                .stream()
                .map(PizzaDto::toDto)
                .collect(Collectors.toList());
    }
}
