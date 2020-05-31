package edu.pwr.pizzeria.model.pizza;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String typeName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizza")
    protected List<PizzaIngredient> ingredients;

    protected BigDecimal price;
    protected int diameter;

    @Enumerated(EnumType.STRING)
    protected PizzaCrust crust;

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
        AbstractPizza that = (AbstractPizza) o;
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
