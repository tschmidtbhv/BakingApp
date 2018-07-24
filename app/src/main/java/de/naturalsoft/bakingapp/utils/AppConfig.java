package de.naturalsoft.bakingapp.utils;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 */
public class AppConfig {

    //The current Database version
    public static final int DATABASE_VERSION = 1;

    //Name for the Database
    public static final String DATABASE_NAME = "backing.db";

    //Base Host URL
    public static final String BASE_URL = "http://go.udacity.com/";


    public static final int INGREDIENT_TYPE = 1;

    public static final int STEPS_TYPE = 2;

    public static final int ADAPTER_MODE_RECIPES = 1;

    public static final int ADAPTER_MODE_RECIPE_WITH_ID = 2;

    public static final int WIDGET_REQUEST_CODE = 0;


    /**
     * Intent Keys
     */
    public static final String RECIPEID = "RECIPEID";

    public static final String RECIPEINCREDIENTSKEY = "RECIPEINCREDIENTSKEY";
}
