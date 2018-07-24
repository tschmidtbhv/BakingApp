package de.naturalsoft.bakingapp.data.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import de.naturalsoft.bakingapp.data.dataObjects.Steps;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 * <p>
 * Converter for Steps List
 */

public class StepsConverter {

    /**
     * Converts the given
     * JSON Stream to List
     *
     * @param data the List as JSON String
     * @return List of Steps Items
     */
    @TypeConverter
    public static List<Steps> stringToList(String data) {

        if (data == null) return Collections.emptyList();

        Type typeList = new TypeToken<List<Steps>>() {
        }.getType();

        Gson gson = new Gson();
        return gson.fromJson(data, typeList);
    }

    /**
     * Converts the given
     * List to JSON String
     *
     * @param steps List of Ingredient Obj
     * @return JSON String
     */
    @TypeConverter
    public static String listToString(List<Steps> steps) {
        Gson gson = new Gson();
        return gson.toJson(steps);
    }
}
