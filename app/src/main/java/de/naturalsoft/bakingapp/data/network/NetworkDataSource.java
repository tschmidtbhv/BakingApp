package de.naturalsoft.bakingapp.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 * <p>
 * DataSource for Network Data
 */
public class NetworkDataSource {

    private final static String CLASSTAG = NetworkDataSource.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static NetworkDataSource sINSTANCE;
    private static Retrofit mRetrofit;

    private static MutableLiveData<List<Receipe>> mDownloadedRecipes;


    private NetworkDataSource(Retrofit retrofit) {

        mRetrofit = retrofit;
        mDownloadedRecipes = new MutableLiveData<>();
    }

    public static NetworkDataSource getInstance(Retrofit retrofit) {

        if (sINSTANCE == null) {
            synchronized (LOCK) {
                sINSTANCE = new NetworkDataSource(retrofit);
            }
        }

        return sINSTANCE;
    }

    /**
     * Load the Recipes from Server
     * Using Retrofit client
     */

    public void loadRecipesFromServer() {

        Log.d(CLASSTAG, "loadRecipesFromServer");
        RecipesClient client = mRetrofit.create(RecipesClient.class);
        client.getRecipes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<List<Receipe>>() {
                    @Override
                    public void onNext(List<Receipe> receipes) {
                        mDownloadedRecipes.postValue(receipes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(CLASSTAG, "response was not successfull " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(CLASSTAG, "loading successfully");
                    }
                });
    }

    /**
     * Returns current
     * Recipes List
     *
     * @return LiveData<List   <   Receipe>>
     */
    public LiveData<List<Receipe>> getCurrentRecipes() {
        return mDownloadedRecipes;
    }
}
