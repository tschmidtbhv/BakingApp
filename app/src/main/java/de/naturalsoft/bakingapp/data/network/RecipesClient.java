package de.naturalsoft.bakingapp.data.network;

import java.util.List;

import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 */
public interface RecipesClient {

    @GET("android-baking-app-json")
    Call<List<Receipe>> getRecipes();
}
