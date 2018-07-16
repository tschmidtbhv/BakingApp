package de.naturalsoft.bakingapp.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.data.database.ReceipeDao;
import de.naturalsoft.bakingapp.data.network.NetworkDataSource;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 */
public class BackingRepository {

    private final static String CLASSTAG = BackingRepository.class.getSimpleName();

    private static final Object LOCK = new Object();

    private static BackingRepository sINSTANCE;
    private static NetworkDataSource mNetworkDataSource;
    private static ReceipeDao mReceipeDao;

    public BackingRepository(ReceipeDao receipeDao, NetworkDataSource networkDataSource) {
        mReceipeDao = receipeDao;
        mNetworkDataSource = networkDataSource;

        RxJavaPlugins.onSingleScheduler(Schedulers.single()).scheduleDirect(() -> {
            int recipesInDatabase = mReceipeDao.countAllRecipes();
            if(recipesInDatabase <= 0){
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

    public LiveData<List<Receipe>> getAllRecipes() {
        return mReceipeDao.getAllRecipes();
    }

    public LiveData<Receipe> getRecipeForId(int id) {
        return mReceipeDao.getRecipeForId(id);
    }

    private void setObserver() {
        LiveData<List<Receipe>> recipes = mNetworkDataSource.getCurrentRecipes();
        recipes.observeForever(recipesFromNetwork ->{
            RxJavaPlugins.onSingleScheduler(Schedulers.single()).scheduleDirect(() -> {
                mReceipeDao.insertRecipes(recipesFromNetwork);
            });
        });
    }
}
