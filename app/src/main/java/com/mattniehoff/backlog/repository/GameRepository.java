package com.mattniehoff.backlog.repository;

import com.mattniehoff.backlog.retrofit.IgdbGameService;
import com.mattniehoff.backlog.retrofit.IgdbGamesClient;

public class GameRepository {
    private IgdbGamesClient client;


    public GameRepository(IgdbGamesClient client) {
        this.client = client;
    }
}
