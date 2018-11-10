package com.mattniehoff.backlog.retrofit;

import com.mattniehoff.backlog.model.igdb.GameDetail;
import com.mattniehoff.backlog.model.igdb.GameSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IgdbGamesClient {
    @GET("games/{id}")
    Call<GameDetail> getGameById(@Path("id") int gameId);

    @GET("games/")
    Call<GameSearchResult> searchGames(@Query("search") String queryString);
}
