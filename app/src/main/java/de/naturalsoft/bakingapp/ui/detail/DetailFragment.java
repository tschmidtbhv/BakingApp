package de.naturalsoft.bakingapp.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.ui.shared.ViewModelFactory;
import de.naturalsoft.bakingapp.ui.shared.fragment.RecyclerFragment;
import de.naturalsoft.bakingapp.utils.AppConfig;
import de.naturalsoft.bakingapp.utils.Injector;

import static de.naturalsoft.bakingapp.utils.AppConfig.ADAPTER_MODE_RECIPE_WITH_ID;

/**
 * BackingApp
 * Created by Thomas Schmidt on 03.07.2018.
 */
public class DetailFragment extends RecyclerFragment {

    private String CLASSTAG = DetailFragment.class.getSimpleName();

    private DetailViewModel detailViewModel;

    public Observer<Receipe> observer = recipe -> {

        if (recipe != null) {
            swapList(recipe.getSteps());
            showDataView();
        } else {
            showLoading();
        }
    };

    public static DetailFragment getInstance() {
        return new DetailFragment();
    }

    @Override
    protected int getFragmentLayoutResourceId() {
        return R.layout.recipes_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelFactory factory = (ViewModelFactory) Injector.provideViewModelFactory(getContext());
        detailViewModel = ViewModelProviders.of(getActivity(), factory).get(DetailViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            Log.d(CLASSTAG, "EXTRAS " + extras.getInt(AppConfig.RECIPEID));
            detailViewModel.getRecipeForId(extras.getInt(AppConfig.RECIPEID)).observe(this, observer);
        }

    }

    @Override
    protected int getAdapterMode() {
        return ADAPTER_MODE_RECIPE_WITH_ID;
    }

    protected DetailViewModel getDetailViewModel() {
        return detailViewModel;
    }
}
