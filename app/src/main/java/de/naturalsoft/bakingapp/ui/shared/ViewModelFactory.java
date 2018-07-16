package de.naturalsoft.bakingapp.ui.shared;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import de.naturalsoft.bakingapp.data.BackingRepository;
import de.naturalsoft.bakingapp.ui.detail.DetailViewModel;
import de.naturalsoft.bakingapp.ui.list.ListViewModel;

/**
 * BackingApp
 * Created by Thomas Schmidt on 03.07.2018.
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final BackingRepository mBackingRepository;

    public ViewModelFactory(BackingRepository repository){
        mBackingRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if(modelClass.isAssignableFrom(ListViewModel.class)){
            return (T) new ListViewModel(mBackingRepository);
        }else if(modelClass.isAssignableFrom(DetailViewModel.class)){
            return (T) new DetailViewModel(mBackingRepository);
        }else {
            throw new IllegalArgumentException("Class does not assignable");
        }
    }
}
