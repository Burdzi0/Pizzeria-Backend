package edu.pwr.pizzeria.model.pizza;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PizzaIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Ingredient ingredient;
    private int quantity;

    public PizzaIngredient() {
    }

    public PizzaIngredient(Ingredient ingredient, int quantity){
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaIngredient that = (PizzaIngredient) o;
        return id == that.id &&
                quantity == that.quantity &&
                ingredient.equals(that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredient, quantity);
    }

    @Override
    public String toString() {
        return "PizzaIngredient{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                '}';
    }
}
