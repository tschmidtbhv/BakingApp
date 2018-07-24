package de.naturalsoft.bakingapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.util.Log;

import java.util.List;

import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.data.database.ReceipeDao;
import de.naturalsoft.bakingapp.data.network.NetworkDataSource;
import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 * <p>
 * App Repository
 */
public class BackingRepository {

    private final static String CLASSTAG = BackingRepository.class.getSimpleName();

    private static final Object LOCK = new Object();

    private static BackingRepository sINSTANCE;
    private static NetworkDataSource mNetworkDataSource;
    private static ReceipeDao mReceipeDao;

    private BackingRepository(ReceipeDao receipeDao, NetworkDataSource networkDataSource) {
        mReceipeDao = receipeDao;
        mNetworkDataSource = networkDataSource;

        RxJavaPlugins.onSingleScheduler(Schedulers.single()).scheduleDirect(() -> {
            int recipesInDatabase = mReceipeDao.countAllRecipes();
            if (recipesInDatabase <= 0) {
                setObserver();
                mNetworkDataSource.loadRecipesFromServer();
            }
        });
    }

    public synchronized static BackingRepository getInstance(ReceipeDao receipeDao, NetworkDataSource networkDataSource) {

        if (sINSTANCE == null) {
            synchronized (LOCK) {
                sINSTANCE = new BackingRepository(receipeDao, networkDataSource);
                Log.d(CLASSTAG, "New Repository created");
            }
        }

        Log.d(CLASSTAG, "Getting Repository");
        return sINSTANCE;
    }

    /**
     * Get all availalbe Recipes
     *
     * @return
     */
    public LiveData<List<Receipe>> getAllRecipes() {
        return mReceipeDao.getAllRecipes();
    }

    /**
     * Get specific Recipe
     * for given id
     *
     * @param id for recipe
     * @return LiveData for Recipe
     */
    public LiveData<Receipe> getRecipeForId(int id) {
        return mReceipeDao.getRecipeForId(id);
    }

    /**
     * Setting the observer
     */
    private void setObserver() {
        LiveData<List<Receipe>> recipes = mNetworkDataSource.getCurrentRecipes();
        recipes.observeForever(getInsertRecipeObserver());
    }

    /**
     * Get Observer
     *
     * @return Observer<List   <   Receipe>>
     */
    private Observer<List<Receipe>> getInsertRecipeObserver() {

        return recipesFromNetwork -> {

            Observable.just(recipesFromNetwork).subscribeOn(Schedulers.io()).subscribe(next -> {
                mReceipeDao.insertRecipes(next);
            }, error -> {
                Log.d(CLASSTAG, "onError while saving recipes");
            }, () -> {
                Log.d(CLASSTAG, "onComplete");
            });
        };
    }
}
