package edu.pwr.pizzeria.model.pizza;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class StandardPizza extends AbstractPizza {

    public StandardPizza() {
        crust = PizzaCrust.CIENKIE;
    }


    public StandardPizza(StandardPizza standardPizza) {
        this.crust = standardPizza.crust;
        this.diameter = standardPizza.diameter;
        this.ingredients = standardPizza.ingredients;
        this.price = standardPizza.price;
        this.typeName = standardPizza.typeName;
    }

    public StandardPizza(String typeName, List<PizzaIngredient> ingredients, int diameter) {
        this.typeName = typeName;
        this.ingredients = ingredients;
        this.diameter = diameter;
    }


}
