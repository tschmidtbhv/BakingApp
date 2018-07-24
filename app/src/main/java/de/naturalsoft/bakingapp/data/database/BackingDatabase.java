package de.naturalsoft.bakingapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.data.database.converter.IngredientsConverter;
import de.naturalsoft.bakingapp.data.database.converter.StepsConverter;
import de.naturalsoft.bakingapp.utils.AppConfig;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 * <p>
 * Application DataBase
 */
@Database(entities = Receipe.class, version = AppConfig.DATABASE_VERSION, exportSchema = false)
@TypeConverters({IngredientsConverter.class, StepsConverter.class})
public abstract class BackingDatabase extends RoomDatabase {

    //Class Tag for logging
    private static final String CLASSTAG = BackingDatabase.class.getSimpleName();

    //Object to syncronize
    private static final Object LOCK = new Object();

    //Instance of the database
    private static BackingDatabase sINSTANCE;

    /**
     * Get the database instance / or create one
     *
     * @param context should be Application Context
     * @return BackingDatabase
     */
    public static BackingDatabase getInstance(Context context) {

        if (sINSTANCE == null) {
            synchronized (LOCK) {
                sINSTANCE = Room.databaseBuilder(context, BackingDatabase.class, AppConfig.DATABASE_NAME).build();
                Log.d(CLASSTAG, "New Database created: " + AppConfig.DATABASE_NAME);
            }
        }

        Log.d(CLASSTAG, "Getting the Database instance");
        return sINSTANCE;
    }

    /**
     * Provicedes Data Access Object
     *
     * @return ReceipeDao
     */
    public abstract ReceipeDao receipeDao();
}
