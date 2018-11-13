package com.mattniehoff.backlog.retrofit;

import com.mattniehoff.backlog.model.igdb.GameDetail;
import com.mattniehoff.backlog.model.igdb.GameSearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IgdbGamesClient {
    @GET("games/{id}/")
    Call<GameDetail> getGameById(@Path("id") int gameId, @Header("user-key") String userkey);

    @GET("games/?limit=5")
    Call<List<GameSearchResult>> searchGames(@Query("search") String queryString, @Header("user-key") String userkey);
}
