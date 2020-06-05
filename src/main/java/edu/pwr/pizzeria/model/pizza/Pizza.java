package edu.pwr.pizzeria.model.pizza;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class Pizza {

    public static final double THICK_CRUST_PRICE = 10.0;
    public static final double THIN_CRUST_PRICE = 7.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PizzaIngredient> ingredients;
    private BigDecimal price20;
    private BigDecimal price30;
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

    public Pizza(Pizza pizza) {
        this.typeName = pizza.typeName;
        this.ingredients = pizza.ingredients;
        this.diameter = pizza.diameter;
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

    public BigDecimal getPrice20() { return price20; }

    public void setPrice20(BigDecimal price) { this.price20 = price; }

    public BigDecimal getPrice30() { return price30; }

    public void setPrice30(BigDecimal price30) { this.price30 = price30; }

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

    public void computePrice() {

        double sum = 0.0;

        switch(crust){
            case GRUBE:
                sum += THICK_CRUST_PRICE;
            case CIENKIE:
                sum += THIN_CRUST_PRICE;
        }

        for (PizzaIngredient ingredient : ingredients) {
            sum += ingredient.getIngredient().getPrice().doubleValue() * ingredient.getQuantity();
        }

        price20 = BigDecimal.valueOf(sum);
        price30 = BigDecimal.valueOf(sum *1.5);
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
                Objects.equals(price20, pizza.price20) &&
                Objects.equals(price30, pizza.price30) &&
                crust == pizza.crust;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, ingredients, price20, price30, diameter, crust);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", ingredients=" + ingredients +
                ", price20=" + price20 +
                ", price30=" + price30 +
                ", diameter=" + diameter +
                ", crust=" + crust +
                '}';
    }
}
