package edu.pwr.pizzeria.pizza;

import edu.pwr.pizzeria.ClientBasedTest;
import edu.pwr.pizzeria.model.PizzaCrust;
import edu.pwr.pizzeria.model.dto.IngredientDto;
import edu.pwr.pizzeria.model.dto.PizzaDto;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

public class PizzaControllerTest extends ClientBasedTest {

    @Autowired
    private PizzaClient pizzaClient;

    private PizzaDto pizzaDto;

    @BeforeEach
    void setUp() {
        pizzaDto = new PizzaDto(1,
                "Test",
                new ArrayList<>() {{
                    add(new IngredientDto(2, "Cheese", 1, BigDecimal.valueOf(450, 2), true));
                }},
                30,
                PizzaCrust.THICK
        );
    }

    @Test
    void testIfPizzasExists() {
        final var pizzas = pizzaClient.getAll();

        assertThat(pizzas).isNotNull();
        assertThat(pizzas).hasSize(2);
    }

    @Test
    void testIfPizzaExists() {
        final PizzaDto pizza = pizzaClient.getPizza(1);

        assertThat(pizza).isNotNull();
        assertThat(pizza).isEqualTo(pizzaDto);
    }

    @Test
    void testForNonExistingPizza() {
        final int id = 9000;
        final FeignException.NotFound exception =
                catchThrowableOfType(() -> pizzaClient.getPizza(id), FeignException.NotFound.class);

        assertThat(exception.status()).isEqualTo(404);
        assertThat(exception.getMessage()).contains("Pizza with id:" + id + " not found");
    }
}
