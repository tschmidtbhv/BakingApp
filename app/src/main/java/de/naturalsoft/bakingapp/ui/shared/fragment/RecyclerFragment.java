package de.naturalsoft.bakingapp.ui.shared.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import de.naturalsoft.bakingapp.R;
import de.naturalsoft.bakingapp.ui.list.RecipeAdapter;
import de.naturalsoft.bakingapp.utils.BakingHelper;

/**
 * BackingApp
 * Created by Thomas Schmidt on 13.07.2018.
 */
public abstract class RecyclerFragment extends BaseFragment {

    RecyclerView recyclerView;
    GridLayoutManager mLayoutManager;
    RecipeAdapter recipeAdapter;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(this.getContext(), BakingHelper.calculateNoOfColumns(this.getContext()));

        recipeAdapter = new RecipeAdapter(getAdapterMode());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recipeAdapter);

        progressBar = rootView.findViewById(R.id.progressbar);
        return rootView;
    }

    protected abstract int getAdapterMode();

    public void swapList(List<?> list){
        recipeAdapter.swapList(list);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void showDataView() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public void showLoading() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }
}
