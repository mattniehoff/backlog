package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.model.igdb.GameDetail;
import com.mattniehoff.backlog.repository.GameRepository;

import java.util.Date;

public class GameDetailViewModel extends ViewModel {
    // GameEntry represents the entry in the database;
    private final LiveData<GameEntry> gameEntry;

    // GameDetail represents the return call from the API
    private final LiveData<GameDetail> gameDetail;

    // For tracking length of the backlog if we add a new one
    private final LiveData<Integer> backlogLength;

    private final GameRepository repository;
    private final int gameId;

    public GameDetailViewModel(GameRepository repository, int gameId) {
        this.repository = repository;
        this.gameId = gameId;
        gameDetail = repository.searchGameById(gameId);
        gameEntry = repository.getGameById(gameId);
        backlogLength = repository.getGameBacklogLength();
    }

    public LiveData<GameDetail> getGameDetails() {
        return this.gameDetail;
    }

    public LiveData<GameEntry> getGameEntry() {
        return this.gameEntry;
    }

    public boolean saveNewCompletedGame() {
        if (gameDetail.getValue() != null) {
            GameEntry entry = new GameEntry(gameDetail.getValue());
            entry.setDateCompleted(new Date());
            repository.insert(entry);
            return true;
        } else {
            return false;
        }
    }

    public void markComplete() {
        if (gameEntry.getValue() != null) {
            GameEntry entry = gameEntry.getValue();
            entry.setDateCompleted(new Date());
            repository.insert(entry);
        } else {
            saveNewCompletedGame();
        }
    }

    public void removeGameFromLibrary() {
        repository.delete(gameEntry.getValue());
    }

    public void saveNewGame() {
        repository.insert(new GameEntry(gameDetail.getValue()));
    }

    public void removeFromBacklog() {
    }

    public void addToBacklog() {
    }
}
