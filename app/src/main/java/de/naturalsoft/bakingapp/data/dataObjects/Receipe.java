package de.naturalsoft.bakingapp.data.dataObjects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import de.naturalsoft.bakingapp.data.database.converter.IngredientsConverter;
import de.naturalsoft.bakingapp.data.database.converter.StepsConverter;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 *
 * POJO Class for Recip
 */
@Entity(tableName = "recipes")
public class Receipe {

    //Unique ID for Recipe
    @PrimaryKey(autoGenerate = true)
    private int uid;

    //Recipe name
    private String name;

    //List of Ingredients
    private List<Ingredients> ingredients;

    //List of Recipe Steps
    private List<Steps> steps;

    //Number of possible servings
    private int servings;

    //Recipe image
    private String image;

    public Receipe(int uid, String name, List<Ingredients> ingredients, List<Steps> steps, int servings, String image) {
        this.uid = uid;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
