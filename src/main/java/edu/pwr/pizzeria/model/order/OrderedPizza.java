package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.pizza.Ingredient;
import edu.pwr.pizzeria.model.pizza.Pizza;
import edu.pwr.pizzeria.model.pizza.PizzaCrust;
import edu.pwr.pizzeria.model.pizza.PizzaIngredient;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class OrderedPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderedIngredient> ingredients;
    private BigDecimal price;
    private int diameter;

    @Enumerated(EnumType.STRING)
    private PizzaCrust crust;

    public OrderedPizza() {
    }

    public OrderedPizza(String typeName, List<OrderedIngredient> ingredients, int diameter) {
        this.typeName = typeName;
        this.ingredients = ingredients;
        this.diameter = diameter;
        crust = PizzaCrust.CIENKIE;
    }

    public OrderedPizza(Pizza pizza) {
        this.typeName = pizza.getTypeName();
        this.ingredients = orderedIngredients(pizza.getIngredients());
        this.diameter = pizza.getDiameter();
        crust = PizzaCrust.CIENKIE;
    }

    private List<OrderedIngredient> orderedIngredients(List<PizzaIngredient> ingredients) {
        return ingredients.stream()
                .map(pizzaIngredient -> {
                    final Ingredient ingredient = pizzaIngredient.getIngredient();
                    return new OrderedIngredient(ingredient.getName(), ingredient.getPrice(), ingredient.isIfAllergen(), pizzaIngredient.getQuantity());
                }).collect(Collectors.toList());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }

    public void setCrust(PizzaCrust crust) {
        this.crust = crust;
    }

    public List<OrderedIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<OrderedIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedPizza that = (OrderedPizza) o;
        return id == that.id &&
                diameter == that.diameter &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(ingredients, that.ingredients) &&
                Objects.equals(price, that.price) &&
                crust == that.crust;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, ingredients, price, diameter, crust);
    }

    @Override
    public String toString() {
        return typeName + "(" + crust + " ciasto, " + diameter + " cm";
    }
}
