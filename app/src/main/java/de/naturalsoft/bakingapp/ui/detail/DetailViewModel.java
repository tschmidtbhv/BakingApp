package de.naturalsoft.bakingapp.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import de.naturalsoft.bakingapp.data.BackingRepository;
import de.naturalsoft.bakingapp.data.dataObjects.Receipe;
import de.naturalsoft.bakingapp.data.dataObjects.Steps;

/**
 * BackingApp
 * Created by Thomas Schmidt on 13.07.2018.
 */
public class DetailViewModel extends ViewModel {

    private final BackingRepository mBackingRepository;
    private final LiveData<Receipe> mRecipe;

    private static MutableLiveData<Steps> mStep = new MutableLiveData<>();

    public DetailViewModel(BackingRepository repository, int recipeId) {
        mBackingRepository = repository;
        mRecipe = mBackingRepository.getRecipeForId(recipeId);
    }

    public LiveData<Receipe> getRecipe(){
        return  mRecipe;
    }

    public MutableLiveData<Steps> getStep() {
        return mStep;
    }

    public void setSteps(Steps step) {
        this.mStep.postValue(step);
    }

}
