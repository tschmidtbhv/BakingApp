package de.naturalsoft.bakingapp.ui.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.ui.shared.ViewModelFactory;
import de.naturalsoft.bakingapp.ui.shared.fragment.RecyclerFragment;
import de.naturalsoft.bakingapp.utils.AppConfig;
import de.naturalsoft.bakingapp.utils.Injector;

/**
 * BackingApp
 * Created by Thomas Schmidt on 03.07.2018.
 */
public class ListFragment extends RecyclerFragment {


    private ListViewModel listViewModel;

    public static ListFragment getInstance() {
        return new ListFragment();
    }

    public Observer<List<Receipe>> observer = recipes -> {

        if (recipes != null && recipes.size() != 0) {
            swapList(recipes);
            showDataView();
        } else {
            showLoading();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelFactory factory = (ViewModelFactory) Injector.provideViewModelFactory(getActivity());
        listViewModel = ViewModelProviders.of(getActivity(), factory).get(ListViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewModel.getRecipes().observe(this, observer);
    }


    @Override
    protected int getFragmentLayoutResourceId() {
        return R.layout.recipes_list;
    }

    @Override
    protected int getAdapterMode() {
        return AppConfig.ADAPTER_MODE_RECIPES;
    }
}
