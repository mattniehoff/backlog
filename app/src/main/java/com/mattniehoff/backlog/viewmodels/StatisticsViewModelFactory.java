package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.mattniehoff.backlog.repository.GameRepository;

public class StatisticsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final GameRepository repository;

    public StatisticsViewModelFactory(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new StatisticsViewModel(repository);
    }
}
