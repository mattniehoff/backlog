package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.repository.GameRepository;

import java.util.List;

public class BacklogViewModel extends ViewModel {
    private final GameRepository repository;
    private final LiveData<List<GameEntry>> gameList;

    public BacklogViewModel(GameRepository repository) {
        this.repository = repository;
        gameList = this.repository.getGameBacklog();
    }

    public LiveData<List<GameEntry>> getGameBacklog() {
        return gameList;
    }
}
