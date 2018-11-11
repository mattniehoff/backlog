package com.mattniehoff.backlog.repository;

import android.app.Application;

import com.mattniehoff.backlog.model.database.AppDatabase;
import com.mattniehoff.backlog.model.database.GameDao;
import com.mattniehoff.backlog.retrofit.IgdbGameService;
import com.mattniehoff.backlog.retrofit.IgdbGamesClient;

public class GameRepository {
    private IgdbGamesClient client;
    private GameDao gameDao;

    public GameRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
    }


    public GameRepository(IgdbGamesClient client) {
        this.client = client;
    }
}
