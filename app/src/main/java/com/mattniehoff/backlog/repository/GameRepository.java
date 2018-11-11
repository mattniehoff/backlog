package com.mattniehoff.backlog.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.mattniehoff.backlog.model.database.AppDatabase;
import com.mattniehoff.backlog.model.database.GameDao;
import com.mattniehoff.backlog.model.igdb.GameDetail;
import com.mattniehoff.backlog.model.igdb.GameSearchResult;
import com.mattniehoff.backlog.retrofit.IgdbGameService;
import com.mattniehoff.backlog.retrofit.IgdbGamesClient;
import com.mattniehoff.backlog.retrofit.NetworkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Resources:
// https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1
public class GameRepository {
    private static String TAG = GameRepository.class.getSimpleName();
    private IgdbGamesClient client;
    private static GameRepository instance;

    private GameRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkUtils.IGDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(IgdbGamesClient.class);
    }

    public synchronized static GameRepository getInstance() {
        if (instance == null) {
            instance = new GameRepository();
        }

        return instance;
    }

    public LiveData<List<GameSearchResult>> getGameIds(String searchQuery) {
        final MutableLiveData<List<GameSearchResult>> data = new MutableLiveData<>();

        client.searchGames(searchQuery, NetworkUtils.IGDB_API_KEY).enqueue(new Callback<List<GameSearchResult>>() {
            @Override
            public void onResponse(Call<List<GameSearchResult>> call, Response<List<GameSearchResult>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<GameSearchResult>> call, Throwable t) {
                data.setValue(null);
                Log.d(TAG, "Failed to search games.");
            }
        });

        return data;

    }

//    public LiveData<GameDetail> getGameDetail(int gameId) {
//
//    }
}
