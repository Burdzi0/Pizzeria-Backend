package edu.pwr.pizzeria.model.pizza;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String typeName;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PizzaIngredient> ingredients;
    private BigDecimal price;
    private int diameter;

    @Enumerated(EnumType.STRING)
    private PizzaCrust crust;

    public Pizza() {
    }

    public Pizza(String typeName, List<PizzaIngredient> ingredients, int diameter) {
        this.typeName = typeName;
        this.ingredients = ingredients;
        this.diameter = diameter;
        crust = PizzaCrust.CIENKIE;
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

    public List<PizzaIngredient> getPizzaIngredients() {
        return ingredients;
    }

    public void setPizzaIngredients(List<PizzaIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<PizzaIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<PizzaIngredient> ingredients) {
        this.ingredients = ingredients;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id == pizza.id &&
                diameter == pizza.diameter &&
                typeName.equals(pizza.typeName) &&
                ingredients.equals(pizza.ingredients) &&
                Objects.equals(price, pizza.price) &&
                crust == pizza.crust;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, ingredients, price, diameter, crust);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", ingredients=" + ingredients +
                ", price=" + price +
                ", diameter=" + diameter +
                ", crust=" + crust +
                '}';
    }
}
