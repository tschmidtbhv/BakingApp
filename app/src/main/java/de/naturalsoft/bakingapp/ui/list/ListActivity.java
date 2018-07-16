package de.naturalsoft.bakingapp.ui.list;

import android.content.Intent;
import android.support.v4.app.Fragment;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.ui.detail.DetailActivity;
import de.naturalsoft.bakingapp.ui.shared.activity.BaseActivity;
import de.naturalsoft.bakingapp.ui.shared.interfaces.OnRecipeListItemListener;
import de.naturalsoft.bakingapp.utils.AppConfig;

public class ListActivity extends BaseActivity implements OnRecipeListItemListener {

    @Override
    public void onItemClicked(Receipe receipe) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(AppConfig.RECIPEID, receipe.getUid());
        startActivity(intent);
    }

    @Override
    protected Fragment getFragment() {
        return ListFragment.getInstance();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }
}
