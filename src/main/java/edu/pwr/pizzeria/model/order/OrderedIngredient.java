package edu.pwr.pizzeria.model.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class OrderedIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal price;
    private boolean ifAllergen;
    private int quantity;

    public OrderedIngredient() {
    }

    public OrderedIngredient(String name, BigDecimal price, boolean ifAllergen, int quantity) {
        this.name = name;
        this.price = price;
        this.ifAllergen = ifAllergen;
        this.quantity = quantity;
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
        OrderedIngredient that = (OrderedIngredient) o;
        return id == that.id &&
                ifAllergen == that.ifAllergen &&
                quantity == that.quantity &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, ifAllergen, quantity);
    }

    @Override
    public String toString() {
        return name + " x " + quantity;
    }
}
