package de.naturalsoft.bakingapp.ui.detail;

import android.support.v4.app.Fragment;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.data.dataObjects.Steps;
import de.naturalsoft.bakingapp.ui.shared.activity.BaseActivity;
import de.naturalsoft.bakingapp.ui.shared.interfaces.OnRecipeListItemListener;
import de.naturalsoft.bakingapp.ui.shared.interfaces.OnStepListItemListener;

/**
 * BackingApp
 * Created by Thomas Schmidt on 13.07.2018.
 */
public class DetailActivity extends BaseActivity implements OnStepListItemListener {

    @Override
    protected Fragment getFragment() {

        Fragment fragment = getCurrentActiviFragment();
        if(fragment == null) fragment = DetailFragment.getInstance();

        replaceFragment(fragment);

        if (isTablet()) {
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

        if (!isTablet()) {
            replaceFragment(DetailVideoFragment.getInstance());
        } else {
            //Update Main Container
        }
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
