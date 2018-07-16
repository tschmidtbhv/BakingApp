package de.naturalsoft.bakingapp.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import de.naturalsoft.bakingapp.data.BackingRepository;
import de.naturalsoft.bakingapp.data.dataObjects.Receipe;

/**
 * BackingApp
 * Created by Thomas Schmidt on 03.07.2018.
 */
public class ListViewModel extends ViewModel {

    private final BackingRepository mBackingRepository;
    private final LiveData<List<Receipe>> mRecipes;

    public ListViewModel(BackingRepository repository) {
        mBackingRepository = repository;
        mRecipes = mBackingRepository.getAllRecipes();
    }

    public LiveData<List<Receipe>> getRecipes() {
        return mRecipes;
    }

}
