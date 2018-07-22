package de.naturalsoft.bakingapp.ui.shared.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import de.naturalsoft.bakingapp.R;

/**
 * BackingApp
 * Created by Thomas Schmidt on 13.07.2018.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static Fragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        setContainerFragment(R.id.container, getFragment());
    }

    protected abstract Fragment getFragment();

    protected Fragment getCurrentActiviFragment(){
        return mFragment;
    }

    protected void replaceFragment(Fragment fragment) {

        mFragment = fragment;
        setContainerFragment(R.id.container, mFragment);
    }

    protected void setContainerFragment(int layoutResourceId, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(layoutResourceId, fragment);

        transaction.commit();
    }

    public void swapFragment(Fragment fragment){
        if(fragment != null)replaceFragment(fragment);
    }

    protected abstract int getLayoutResource();

    public boolean isTablet(){
        return getResources().getBoolean(R.bool.is_tablet);
    }

    public boolean isLandscapeMode() {
        return getResources().getBoolean(R.bool.is_landscape);
    }
}
