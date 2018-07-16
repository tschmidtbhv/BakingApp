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

    private static Fragment mFragment;
    private static final Object LOCK = new Object();

    @Override
    protected Fragment getFragment() {

        synchronized (LOCK){
            if(mFragment == null){
                mFragment = DetailFragment.getInstance();
                if(isTablet()){
                   setContainerFragment(R.id.main_container, DetailVideoFragment.getInstance());
                }
            }
        }
        return mFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail;
    }

    @Override
    public void onItemClicked(Steps step) {

        ((DetailFragment)getCurrentActiviFragment()).getDetailViewModel().setSteps(step);

        if(!isTablet()) {
            mFragment = DetailVideoFragment.getInstance();
            replaceFragment(mFragment);
        }else {
            //Update Main Container
        }
    }

    @Override
    public void onBackPressed() {
        if(mFragment.getClass().isAssignableFrom(DetailVideoFragment.class)){
            mFragment = DetailFragment.getInstance();
            swapFragment(mFragment);
        }else {
            super.onBackPressed();
        }
    }
}
