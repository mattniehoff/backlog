package com.mattniehoff.backlog.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.mattniehoff.backlog.AppExecutors;
import com.mattniehoff.backlog.model.database.AppDatabase;
import com.mattniehoff.backlog.model.database.GameDao;
import com.mattniehoff.backlog.model.database.GameEntry;
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
// https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html?index=..%2F..%2Findex#0
public class GameRepository {
    private static String TAG = GameRepository.class.getSimpleName();

    // For singleton instances
    private static final Object LOCK = new Object();
    private static GameRepository INSTANCE;
    private final GameDao gameDao;
    private final AppExecutors executors;
    private boolean mInitialized = false;

    private GameRepository(GameDao gameDao,
                           AppExecutors executors) {
        this.gameDao = gameDao;
        this.executors = executors;
    }

    public synchronized static GameRepository getInstance(
            GameDao gameDao, AppExecutors executors) {
        Log.d(TAG, "Getting the repository");
        if (INSTANCE == null) {
            synchronized (LOCK) {
                INSTANCE = new GameRepository(gameDao,
                        executors);
                Log.d(TAG, "Made new repository");
            }
        }
        return INSTANCE;
    }

    // Database operations
    public LiveData<List<GameEntry>> getGameLibrary() {
        return gameDao.getAllGames();
    }

    public LiveData<List<GameEntry>> getGameBacklog() {
        return gameDao.getAllBacklogGames();
    }

    public LiveData<GameEntry> getGameById(int gameId) {
        return gameDao.getGameById(gameId);
    }


//    private IgdbGamesClient client;
//    private static GameRepository instance;
//
//    private GameRepository(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(NetworkUtils.IGDB_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        client = retrofit.create(IgdbGamesClient.class);
//    }
//
//    public synchronized static GameRepository getInstance() {
//        if (instance == null) {
//            instance = new GameRepository();
//        }
//
//        return instance;
//    }
//
//    public LiveData<List<GameSearchResult>> getGameIds(String searchQuery) {
//        final MutableLiveData<List<GameSearchResult>> data = new MutableLiveData<>();
//
//        client.searchGames(searchQuery, NetworkUtils.IGDB_API_KEY).enqueue(new Callback<List<GameSearchResult>>() {
//            @Override
//            public void onResponse(Call<List<GameSearchResult>> call, Response<List<GameSearchResult>> response) {
//                data.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<GameSearchResult>> call, Throwable t) {
//                data.setValue(null);
//                Log.d(TAG, "Failed to search games.");
//            }
//        });
//
//        return data;
//
//    }
//
////    public LiveData<GameDetail> getGameDetail(int gameId) {
////
////    }
}
