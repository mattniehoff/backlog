package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.mattniehoff.backlog.repository.GameRepository;

public class LibraryViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final GameRepository repository;

    public LibraryViewModelFactory(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new LibraryViewModel(repository);
    }
}
