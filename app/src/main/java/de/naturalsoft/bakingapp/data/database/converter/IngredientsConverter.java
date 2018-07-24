package de.naturalsoft.bakingapp.data.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import de.naturalsoft.bakingapp.data.dataObjects.Ingredients;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018
 *
 * Converter for Ingredients List
 */
public class IngredientsConverter {


    /**
     * Converts the given
     * JSON Stream to List
     * @param data the List as JSON String
     * @return List of Ingredients Items
     */
    @TypeConverter
    public static List<Ingredients> stringToList(String data) {

        if (data == null) return Collections.emptyList();

        Type typeList = new TypeToken<List<Ingredients>>() {}.getType();

        Gson gson = new Gson();
        return gson.fromJson(data, typeList);
    }


    /**
     * Converts the given
     * List to JSON String
     * @param ingredients List of Ingredient Obj
     * @return JSON String
     */
    @TypeConverter
    public static String listToString(List<Ingredients> ingredients) {
        Gson gson = new Gson();
        return gson.toJson(ingredients);
    }
}
