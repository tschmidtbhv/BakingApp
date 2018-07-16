package de.naturalsoft.bakingapp.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;

import java.util.List;

import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 */
public class NetworkDataSource {

    private final static String CLASSTAG = NetworkDataSource.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static NetworkDataSource sINSTANCE;
    private static Context mContext;
    private static Retrofit mRetrofit;

    private static MutableLiveData<List<Receipe>> mDownloadedRecipes;


    private NetworkDataSource(Context context, Retrofit retrofit) {
        mContext = context;
        mRetrofit = retrofit;

        mDownloadedRecipes = new MutableLiveData<>();
    }

    public static NetworkDataSource getInstance(Context context, Retrofit retrofit) {

        if (sINSTANCE == null) {
            synchronized (LOCK) {
                sINSTANCE = new NetworkDataSource(context, retrofit);
            }
        }

        return sINSTANCE;
    }



    public void loadRecipesFromServer() {

        Log.d(CLASSTAG, "loadRecipesFromServer");
        RecipesClient client = mRetrofit.create(RecipesClient.class);
        Call<List<Receipe>> call = client.getRecipes();

        if (call != null) {
            doCall(call);
        }
    }

    private void doCall(Call<List<Receipe>> call) {

        call.enqueue(new Callback<List<Receipe>>() {

            @Override
            public void onResponse(Call<List<Receipe>> call, Response<List<Receipe>> response) {
                if(response.isSuccessful()) {
                    mDownloadedRecipes.postValue(response.body());
                }else {
                    Log.d(CLASSTAG, "response was not successfull " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Receipe>> call, Throwable t) {
                Log.d(CLASSTAG, "onFailure " + t.getLocalizedMessage());
            }
        });
    }

    public LiveData<List<Receipe>> getCurrentRecipes() {
        return mDownloadedRecipes;
    }
}
