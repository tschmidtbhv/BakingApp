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
    private static MutableLiveData<Steps> mStep = new MutableLiveData<>();

    public DetailViewModel(BackingRepository repository){
        mBackingRepository = repository;
    }

    public LiveData<Receipe> getRecipeForId(int id){
        return mBackingRepository.getRecipeForId(id);
    }

    public MutableLiveData<Steps> getStep() {
        return mStep;
    }

    public void setSteps(Steps step) {
        this.mStep.postValue(step);
    }

}
