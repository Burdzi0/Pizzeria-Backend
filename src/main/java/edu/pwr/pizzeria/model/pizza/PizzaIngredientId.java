package edu.pwr.pizzeria.model.pizza;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PizzaIngredientId implements Serializable {

    @Column(name = "PIZZA_ID")
    private Integer pizzaId;

    @Column(name = "INGREDIENT_ID")
    private Integer ingredientId;

    public PizzaIngredientId() {
    }

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaIngredientId that = (PizzaIngredientId) o;
        return Objects.equals(pizzaId, that.pizzaId) &&
                Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId, ingredientId);
    }

    @Override
    public String toString() {
        return "PizzaIngredientPK{" +
                "pizzaId=" + pizzaId +
                ", ingredientId=" + ingredientId +
                '}';
    }
}
