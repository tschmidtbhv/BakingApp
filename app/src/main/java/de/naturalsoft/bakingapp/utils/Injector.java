package de.naturalsoft.bakingapp.utils;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import de.naturalsoft.bakingapp.data.BackingRepository;
import de.naturalsoft.bakingapp.data.database.BackingDatabase;
import de.naturalsoft.bakingapp.data.network.NetworkDataSource;
import de.naturalsoft.bakingapp.ui.shared.ViewModelFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * BackingApp
 * Created by Thomas Schmidt on 02.07.2018.
 */
public interface Injector {

    static <T> ViewModelProvider.NewInstanceFactory provideViewModelFactory(Context context, int recipeId) {

        BackingRepository backingRepository = provideBackingRepository(context.getApplicationContext(), provideRetrofit());
        return new ViewModelFactory(backingRepository, recipeId);
    }


    static <T> ViewModelProvider.NewInstanceFactory provideViewModelFactory(Context context) {

        BackingRepository backingRepository = provideBackingRepository(context.getApplicationContext(), provideRetrofit());
        return new ViewModelFactory(backingRepository);
    }

    /**
     * Provides the App Repository
     *
     * @param context Current Context
     * @return BackingRepository Object
     */
    static BackingRepository provideBackingRepository(Context context, Retrofit retrofit) {

        BackingDatabase database = BackingDatabase.getInstance(context.getApplicationContext());
        NetworkDataSource networkDataSource = NetworkDataSource.getInstance(context.getApplicationContext(), retrofit);

        return BackingRepository.getInstance(database.receipeDao(), networkDataSource);
    }

    /**
     * Provides a Retrofit instance
     *
     * @return Retrofit Object
     */
    static Retrofit provideRetrofit() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

}
