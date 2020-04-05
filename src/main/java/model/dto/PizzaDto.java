package model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Ingredient;
import model.Pizza;
import model.PizzaCrust;

import javax.persistence.OneToMany;
import java.util.List;

public class PizzaDto {

    private final String typeName;
    @OneToMany
    private final List<Ingredient> ingredients;
    private final int diameter;
    private final PizzaCrust crust;

    @JsonCreator
    public PizzaDto(@JsonProperty("typeName") String typeName,
                    @JsonProperty("ingredients") List<Ingredient> ingredients,
                    @JsonProperty("diameter") int diameter,
                    @JsonProperty("crust") PizzaCrust crust) {
        this.typeName = typeName;
        this.ingredients = ingredients;
        this.diameter = diameter;
        this.crust = crust;
    }

    public static PizzaDto toDto(Pizza pizza) {
        return new PizzaDto(pizza.getTypeName(),
                pizza.getIngredients(),
                pizza.getDiameter(),
                pizza.getCrust());
    }

    public String getTypeName() {
        return typeName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getDiameter() {
        return diameter;
    }

    public PizzaCrust getCrust() {
        return crust;
    }
}
