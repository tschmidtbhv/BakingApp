package de.naturalsoft.bakingapp.ui.ingredients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Ingredients;
import de.naturalsoft.bakingapp.utils.AppConfig;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IngredientsActivity extends AppCompatActivity {

    private static final String CLASSTAG = IngredientsActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private IngredientsAdapter ingredientsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.ingredientsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ingredientsAdapter = new IngredientsAdapter();
        recyclerView.setAdapter(ingredientsAdapter);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Observable.just(extras.getString(AppConfig.RECIPEINCREDIENTSKEY)).subscribeOn(Schedulers.io()).subscribe(next-> {
                Log.d(IngredientsActivity.class.getSimpleName(), "Thread: " + Thread.currentThread());
                List<Ingredients> ingredients = convertToIngredients(next);
                ingredientsAdapter.swapList(ingredients);
            },error -> {
                Log.d(CLASSTAG, error.getLocalizedMessage());
            },() ->{
                Log.d(CLASSTAG, "onComplete");
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private List<Ingredients> convertToIngredients(String json) {

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Ingredients>>() {
        }.getType();
        return (List<Ingredients>) gson.fromJson(json, listType);
    }


}
