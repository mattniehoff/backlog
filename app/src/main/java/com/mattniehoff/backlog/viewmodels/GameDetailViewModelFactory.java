package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.mattniehoff.backlog.repository.GameRepository;

public class GameDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final GameRepository repository;
    private final int gameId;

    public GameDetailViewModelFactory(GameRepository repository, int gameId) {
        this.repository = repository;
        this.gameId = gameId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new GameDetailViewModel(repository, gameId);
    }
}
