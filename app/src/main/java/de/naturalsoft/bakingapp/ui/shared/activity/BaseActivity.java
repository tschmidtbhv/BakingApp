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

    /**
     * Every Activity which
     * extends need to create
     * a Fragment which sets
     * to the Container
     * <p>
     * Note: It`s important to
     * have an container with the id
     * R.id.container as default
     *
     * @return Fragment
     */
    protected abstract Fragment getFragment();

    /**
     * Returns the current Fragment
     *
     * @return Fragment
     */
    protected Fragment getCurrentActiviFragment() {
        return mFragment;
    }

    /**
     * Replace the Fragment
     * with given Fragment
     *
     * @param fragment new Fragment
     */
    protected void replaceFragment(Fragment fragment) {

        mFragment = fragment;
        setContainerFragment(R.id.container, mFragment);
    }

    /**
     * Replace the Fragment
     * in Container id
     *
     * @param layoutResourceId should be Container ID
     * @param fragment         to replace
     */
    protected void setContainerFragment(int layoutResourceId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(layoutResourceId, fragment);

        transaction.commit();
    }

    /**
     * Swap the Fragment
     * when not NULL
     *
     * @param fragment new Fragment
     */
    public void swapFragment(Fragment fragment) {
        if (fragment != null) replaceFragment(fragment);
    }

    /**
     * Every Activity need
     * to define a Layout Resource
     * this resource will set
     *
     * @return layout recource id
     */
    protected abstract int getLayoutResource();

}
