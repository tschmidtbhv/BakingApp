package de.naturalsoft.bakingapp.data.dataObjects;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 * <p>
 * POJO Class for Ingredients
 */
public class Ingredients {

    private double quantity;
    private String measure;
    private String ingredient;

    public Ingredients(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
