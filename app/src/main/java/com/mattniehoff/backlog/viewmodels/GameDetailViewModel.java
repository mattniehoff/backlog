package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.repository.GameRepository;

public class GameDetailViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final LiveData<GameEntry> gameEntry;

    private final GameRepository repository;
    private final int gameId;

    public GameDetailViewModel(GameRepository repository, int gameId) {
        this.repository = repository;
        this.gameId = gameId;
        gameEntry = repository.getGameById(gameId);
    }
}
