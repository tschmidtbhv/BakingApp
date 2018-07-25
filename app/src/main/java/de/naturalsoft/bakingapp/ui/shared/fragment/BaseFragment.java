package de.naturalsoft.bakingapp.ui.shared.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * BackingApp
 * Created by Thomas Schmidt on 13.07.2018.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(getFragmentLayoutResourceId(), container, false);
        return rootView;
    }

    /**
     * Every Fragment need
     * to define a Layout Resource
     * this resource will set
     *
     * @return layout recource id
     */
    protected abstract int getFragmentLayoutResourceId();
}
