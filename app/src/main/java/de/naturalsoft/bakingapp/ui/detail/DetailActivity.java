package de.naturalsoft.bakingapp.ui.detail;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.data.dataObjects.Steps;
import de.naturalsoft.bakingapp.ui.ingredients.IngredientsActivity;
import de.naturalsoft.bakingapp.ui.shared.activity.BaseActivity;
import de.naturalsoft.bakingapp.ui.shared.interfaces.OnStepListItemListener;
import de.naturalsoft.bakingapp.utils.AppConfig;

import static de.naturalsoft.bakingapp.utils.BakingHelper.isTablet;

/**
 * BackingApp
 * Created by Thomas Schmidt on 13.07.2018.
 */
public class DetailActivity extends BaseActivity implements OnStepListItemListener {

    @Override
    protected Fragment getFragment() {

        Fragment fragment = getCurrentActiviFragment();
        if (fragment == null) fragment = DetailFragment.getInstance();

        replaceFragment(fragment);

        if (isTablet(this)) {
            setContainerFragment(R.id.main_container, DetailVideoFragment.getInstance());
        }

        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail;
    }

    @Override
    public void onItemClicked(Steps step) {

        ((DetailFragment) getCurrentActiviFragment()).getDetailViewModel().setSteps(step);

        if (!isTablet(this)) {
            replaceFragment(DetailVideoFragment.getInstance());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.ingredients) {

            Intent intent = new Intent(this, IngredientsActivity.class);
            LiveData<Receipe> recipe = ((ViewModelInterface) getCurrentActiviFragment()).getViewModel().getRecipe();

            Gson gson = new Gson();
            gson.toJson(recipe.getValue().getIngredients());
            intent.putExtra(AppConfig.RECIPEINCREDIENTSKEY, gson.toJson(recipe.getValue().getIngredients()));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getCurrentActiviFragment().getClass().isAssignableFrom(DetailVideoFragment.class)) {

            swapFragment(DetailFragment.getInstance());
        } else {
            super.onBackPressed();
        }
    }
}
