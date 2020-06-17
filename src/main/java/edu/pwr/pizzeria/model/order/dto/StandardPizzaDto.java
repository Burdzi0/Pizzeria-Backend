package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardPizzaDto {

    private final int id;
    private final int diameter;

    @JsonCreator
    public StandardPizzaDto(@JsonProperty("id") int id,
                            @JsonProperty("diameter") int diameter){
        this.id = id;
        this.diameter = diameter;
    }

    public int getId() {
        return id;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public String toString() {
        return "StandardPizzaDto{" +
                "id=" + id +
                ", diameter=" + diameter +
                '}';
    }
}
