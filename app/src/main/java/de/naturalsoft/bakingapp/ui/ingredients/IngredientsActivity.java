package de.naturalsoft.bakingapp.ui.ingredients;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Ingredients;
import de.naturalsoft.bakingapp.utils.AppConfig;
import de.naturalsoft.bakingapp.utils.BakingHelper;
import de.naturalsoft.bakingapp.widget.BakingWidgetProvider;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IngredientsActivity extends AppCompatActivity {

    private static final String CLASSTAG = IngredientsActivity.class.getSimpleName();

    private static String mIngredientsJSON;

    private RecyclerView recyclerView;
    private IngredientsAdapter ingredientsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incredients);

        initBase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            mIngredientsJSON = extras.getString(AppConfig.RECIPEINCREDIENTSKEY);

            Observable.just(mIngredientsJSON).subscribeOn(Schedulers.io()).subscribe(next -> {
                Log.d(IngredientsActivity.class.getSimpleName(), "Thread: " + Thread.currentThread());

                List<Ingredients> ingredients = BakingHelper.convertToIngredients(next);
                ingredientsAdapter.swapList(ingredients);
            }, error -> {
                Log.d(CLASSTAG, error.getLocalizedMessage());
            }, () -> {
                Log.d(CLASSTAG, "onComplete");
            });

        }


    }

    /**
     * Default setup for
     * ActionBar, RecyclerView ...
     */
    private void initBase() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.ingredientsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ingredientsAdapter = new IngredientsAdapter();
        recyclerView.setAdapter(ingredientsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.ingredients, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addWidget) {
            saveIntoPreferences();
            updateWidget();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Save ingredients JSON
     * in SharedPreferences
     */
    private void saveIntoPreferences() {

        if (mIngredientsJSON != null) {
            SharedPreferences sharedPreferences = getSharedPreferences(AppConfig.APP_SHAREDPREF_FILE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(AppConfig.RECIPEINCREDIENTSKEY, mIngredientsJSON);
            editor.apply();
        }
    }

    /**
     * Updates the Widget
     */
    private void updateWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_listview);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
