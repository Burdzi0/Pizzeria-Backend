package edu.pwr.pizzeria.model.order.dto;

import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.OrderedPizza;
import edu.pwr.pizzeria.model.pizza.PizzaCrust;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderedPizzaDto {

    private int diameter;
    private PizzaCrust crust;
    private BigDecimal price;
    private List<OrderedIngredientDto> pizzaIngredients;

    public OrderedPizzaDto(int diameter, PizzaCrust crust, BigDecimal price, List<OrderedIngredientDto> pizzaIngredients) {
        this.diameter = diameter;
        this.crust = crust;
        this.price = price;
        this.pizzaIngredients = pizzaIngredients;
    }

    public static OrderedPizzaDto toDto(CustomPizza customPizza) {
        return new OrderedPizzaDto(customPizza.getDiameter(),
                customPizza.getCrust(),
                customPizza.getPrice(),
                customPizza.getPizzaIngredients()
                        .stream()
                        .map(OrderedIngredientDto::toDto)
                        .collect(toList()));
    }

    public static OrderedPizzaDto toDto(OrderedPizza customPizza) {
        return new OrderedPizzaDto(customPizza.getDiameter(),
                customPizza.getCrust(),
                customPizza.getPrice(),
                customPizza.getIngredients()
                        .stream()
                        .map(OrderedIngredientDto::toDto)
                        .collect(toList()));
    }

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<OrderedIngredientDto> getPizzaIngredients() {
        return pizzaIngredients;
    }
}
