package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.repository.GameRepository;

import java.util.List;

public class StatisticsViewModel extends ViewModel {
    private final GameRepository repository;
    private final LiveData<List<GameEntry>> gameList;

    public StatisticsViewModel(GameRepository repository) {
        this.repository = repository;
        this.gameList = this.repository.getGameLibrary();
    }

    public LiveData<List<GameEntry>> getGameLiibrary() {
        return gameList;
    }
}

