package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.model.igdb.GameDetail;
import com.mattniehoff.backlog.repository.GameRepository;

public class GameDetailViewModel extends ViewModel {
    // GameEntry represents the entry in the database;
    private final LiveData<GameEntry> gameEntry;

    // GameDetail represents the return call from the API
    private final LiveData<GameDetail> gameDetail;

    private final GameRepository repository;
    private final int gameId;

    public GameDetailViewModel(GameRepository repository, int gameId) {
        this.repository = repository;
        this.gameId = gameId;
        gameDetail = repository.searchGameById(gameId);
        gameEntry = repository.getGameById(gameId);
    }

    public LiveData<GameDetail> getGameDetails() {
        return this.gameDetail;
    }

    public LiveData<GameEntry> getGameEntry() {
        return this.gameEntry;
    }
}
