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

    //Adapter Mode for all Recipes
    public static final int ADAPTER_MODE_RECIPES = 1;

    //Adapter Moder for specific Recipe
    public static final int ADAPTER_MODE_RECIPE_WITH_ID = 2;

    public static final int WIDGET_REQUEST_CODE = 0;

    public static final String APP_SHAREDPREF_FILE = "de.naturalsoft.bakingapp";

    /**
     * Intent Keys
     */
    public static final String RECIPEID = "RECIPEID";

    public static final String RECIPEINCREDIENTSKEY = "RECIPEINCREDIENTSKEY";
}
