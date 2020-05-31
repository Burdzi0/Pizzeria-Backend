package edu.pwr.pizzeria.model.pizza;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal price;
    private boolean ifAllergen;

    public Ingredient() {
    }

    public Ingredient(String name, BigDecimal price, boolean ifAllergen) {
        this.name = name;
        this.price = price;
        this.ifAllergen = ifAllergen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isIfAllergen() {
        return ifAllergen;
    }

    public void setIfAllergen(boolean ifAllergen) {
        this.ifAllergen = ifAllergen;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ifAllergen=" + ifAllergen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id &&
                ifAllergen == that.ifAllergen &&
                name.equals(that.name) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, ifAllergen);
    }
}
