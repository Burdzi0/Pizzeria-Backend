package edu.pwr.pizzeria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int quantity;
    private BigDecimal price;
    private boolean ifAllergen;

    public Ingredient() {
    }

    public Ingredient(String name, int quantity, BigDecimal price, boolean ifAllergen) {
        this.name = name;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
                ", quantity=" + quantity +
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
                quantity == that.quantity &&
                ifAllergen == that.ifAllergen &&
                name.equals(that.name) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price, ifAllergen);
    }
}
