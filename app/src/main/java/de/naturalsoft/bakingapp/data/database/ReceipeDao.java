package de.naturalsoft.bakingapp.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import de.naturalsoft.bakingapp.data.dataObjects.Receipe;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 * <p>
 * Data Access Object
 * Querys for the Database
 */
@Dao
public interface ReceipeDao {

    @Query("SELECT * FROM recipes")
    LiveData<List<Receipe>> getAllRecipes();

    @Query("SELECT * FROM recipes WHERE uid = :id")
    LiveData<Receipe> getRecipeForId(int id);

    @Query("SELECT COUNT(uid) FROM recipes")
    int countAllRecipes();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRecipes(List<Receipe> recipesFromNetwork);
}
