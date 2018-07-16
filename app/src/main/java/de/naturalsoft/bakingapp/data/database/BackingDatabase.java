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
 */
@Database(entities = Receipe.class, version = AppConfig.DATABASE_VERSION, exportSchema = false)
@TypeConverters({IngredientsConverter.class, StepsConverter.class})
public abstract class BackingDatabase extends RoomDatabase{

    private static final String CLASSTAG = BackingDatabase.class.getSimpleName();

    private static final Object LOCK = new Object();

    private static BackingDatabase sINSTANCE;

    /* Use the Application Context to get the Database
     * otherwise the app will have leaks
     */
    public static BackingDatabase getInstance(Context context){

        if(sINSTANCE == null){
            synchronized (LOCK){
                sINSTANCE = Room.databaseBuilder(context,BackingDatabase.class, AppConfig.DATABASE_NAME).build();
                Log.d(CLASSTAG, "New Database created: " + AppConfig.DATABASE_NAME);
            }
        }

        Log.d(CLASSTAG, "Getting the Database instance");
        return sINSTANCE;
    }

    public abstract ReceipeDao receipeDao();
}
