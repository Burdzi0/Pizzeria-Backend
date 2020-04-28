package edu.pwr.pizzeria.model;

import javax.persistence.*;
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
    private int diameter;

    @Enumerated(EnumType.STRING)
    private PizzaCrust crust;

    public Pizza() {
    }

    public Pizza(String typeName, List<PizzaIngredient> ingredients, int diameter) {
        this.typeName = typeName;
        this.ingredients = ingredients;
        this.diameter = diameter;
        crust = PizzaCrust.THIN;
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
                Objects.equals(crust, pizza.crust);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, ingredients, diameter, crust);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", ingredients=" + ingredients +
                ", diameter=" + diameter +
                ", crust='" + crust + '\'' +
                '}';
    }
}
