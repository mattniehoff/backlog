package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.repository.GameRepository;

import java.util.List;

public class LibraryViewModel extends ViewModel {
    private final GameRepository repository;
    private final LiveData<List<GameEntry>> gameList;

    public LibraryViewModel(GameRepository repository) {
        this.repository = repository;
        gameList = this.repository.getGameLibrary();
    }

    public LiveData<List<GameEntry>> getGameLibrary() {
        return gameList;
    }
}
