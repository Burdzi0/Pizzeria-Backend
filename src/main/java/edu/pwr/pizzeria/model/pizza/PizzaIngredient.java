package edu.pwr.pizzeria.model.pizza;

import javax.persistence.*;

@Entity
@Table(name = "pizza_ingredient_with_quantity")
public class PizzaIngredient {

    @EmbeddedId
    private PizzaIngredientId id = new PizzaIngredientId();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("PIZZA_ID")
    @JoinColumn(name = "PIZZA_ID")
    private AbstractPizza pizza;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("INGREDIENT_ID")
    @JoinColumn(name = "INGREDIENT_ID")
    private Ingredient ingredient;

    private int quantity;

    public PizzaIngredient() {
    }

    public PizzaIngredient(AbstractPizza pizza, Ingredient ingredient, int quantity) {
        this.pizza = pizza;
        this.ingredient = ingredient;
        this.quantity = quantity;
        id.setPizzaId(pizza.getId());
        id.setIngredientId(ingredient.getId());
    }

    public PizzaIngredientId getId() {
        return id;
    }

    public void setId(PizzaIngredientId id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public AbstractPizza getPizza() {
        return pizza;
    }

    public void setPizza(AbstractPizza pizza) {
        this.pizza = pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
