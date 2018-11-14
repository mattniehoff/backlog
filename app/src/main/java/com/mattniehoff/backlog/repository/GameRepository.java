package com.mattniehoff.backlog.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.mattniehoff.backlog.AppExecutors;
import com.mattniehoff.backlog.model.database.GameDao;
import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.model.igdb.GameDetail;
import com.mattniehoff.backlog.model.igdb.GameSearchResult;
import com.mattniehoff.backlog.retrofit.IgdbGamesClient;
import com.mattniehoff.backlog.retrofit.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Resources:
// https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1
// https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html?index=..%2F..%2Findex#0
// https://developer.android.com/jetpack/docs/guide#recommended-app-arch
public class GameRepository {
    private static String TAG = GameRepository.class.getSimpleName();

    // For singleton instances
    private static final Object LOCK = new Object();
    private static GameRepository INSTANCE;
    private final GameDao gameDao;
    private final AppExecutors executors;
    private final IgdbGamesClient webservice;
    private boolean mInitialized = false;

    private GameRepository(GameDao gameDao,
                           IgdbGamesClient webservice,
                           AppExecutors executors) {
        this.gameDao = gameDao;
        this.webservice = webservice;
        this.executors = executors;
    }

    public synchronized static GameRepository getInstance(
            GameDao gameDao, IgdbGamesClient webservice, AppExecutors executors) {
        Log.d(TAG, "Getting the repository");
        if (INSTANCE == null) {
            synchronized (LOCK) {
                INSTANCE = new GameRepository(gameDao, webservice,
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

    public LiveData<Integer> getGameBacklogLength() {
        return gameDao.getBacklogCount();
    }

    public LiveData<GameEntry> getGameById(int gameId) {
        return gameDao.getGameById(gameId);
    }

    // Network operations
    public LiveData<GameDetail> searchGameById(int gameId) {
        final MutableLiveData<GameDetail> data = new MutableLiveData<>();
        webservice.getGameById(gameId, NetworkUtils.IGDB_API_KEY).enqueue(new Callback<List<GameDetail>>() {
            @Override
            public void onResponse(Call<List<GameDetail>> call, Response<List<GameDetail>> response) {
                List<GameDetail> detailResult = response.body();
                if (detailResult != null && detailResult.size() > 0) {
                    GameDetail detail = detailResult.get(0);
                    data.setValue(detail);
                }
            }

            @Override
            public void onFailure(Call<List<GameDetail>> call, Throwable t) {
                Log.e(TAG, "Failed to retrieve GameDetail in searchGameById for id " + gameId);
            }
        });

        return data;
    }

    public LiveData<List<GameEntry>> searchGamesByQueryString(String queryString) {
        final MutableLiveData<List<GameEntry>> data = new MutableLiveData<>();
        if (queryString.length() > 0) {

            webservice.searchGames(queryString, NetworkUtils.IGDB_API_KEY).enqueue(new Callback<List<GameSearchResult>>() {
                @Override
                public void onResponse(Call<List<GameSearchResult>> call, Response<List<GameSearchResult>> response) {

                    // Save the response so we can iterate
                    List<GameSearchResult> results = response.body();

                    // New list to save results to
                    List<GameEntry> convertedResults = new ArrayList<>();
                    if (results != null && results.size() > 0) {

                        // Iterate through Ids and go get more data
                        for (GameSearchResult result : results) {
                            webservice.getGameById(result.getId(), NetworkUtils.IGDB_API_KEY).enqueue(new Callback<List<GameDetail>>() {
                                @Override
                                public void onResponse(Call<List<GameDetail>> call, Response<List<GameDetail>> response) {
                                    List<GameDetail> detailResult = response.body();
                                    if (detailResult != null && detailResult.size() > 0) {
                                        GameDetail detail = detailResult.get(0);
                                        if (detail != null) {
                                            convertedResults.add(
                                                    new GameEntry(detail));
                                        }

                                        data.setValue(convertedResults);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<GameDetail>> call, Throwable t) {
                                    Log.e(TAG, "Failed to retrieve GameDetail for id " + result.getId());
                                }
                            });
                        }
                    }
                }

//
//                        data.setValue(response.body());
//                    }
//                }

                @Override
                public void onFailure(Call<List<GameSearchResult>> call, Throwable t) {
                    Log.e(TAG, "Failed to retrieve data from API query");
                }
            });
        }

        return data;
    }

    public void insert(GameEntry entry) {
        new insertAsyncTask(gameDao).execute(entry);
    }

    public void delete(GameEntry entry) {
        new deleteAsyncTask(gameDao).execute(entry);
    }

    public void updateLaterBacklog(int backlogPriority) {
       // gameDao.updateLaterBacklog(backlogPriority);
        new updateBacklogAsyncTask(gameDao).execute(backlogPriority);
    }

    public void clearOtherNowPlaying(Integer gameId) {
        new clearOtherNowPlayingAsyncTask(gameDao).execute(gameId);
    }


    private static class insertAsyncTask extends AsyncTask<GameEntry, Void, Void> {

        private GameDao gameDao;

        insertAsyncTask(GameDao dao) {
            gameDao = dao;
        }

        @Override
        protected Void doInBackground(final GameEntry... params) {
            gameDao.insertGame(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<GameEntry, Void, Void> {

        private GameDao gameDao;

        deleteAsyncTask(GameDao dao) {
            gameDao = dao;
        }

        @Override
        protected Void doInBackground(final GameEntry... params) {
            gameDao.deleteGame(params[0]);
            return null;
        }
    }


    private static class clearOtherNowPlayingAsyncTask extends AsyncTask<Integer, Void, Void> {
        private GameDao gameDao;

        clearOtherNowPlayingAsyncTask(GameDao dao) { gameDao = dao; }

        @Override
        protected Void doInBackground(final Integer... params) {
            gameDao.clearOtherNowPlaying(params[0]);
            return null;
        }
    }

    private static class updateBacklogAsyncTask extends AsyncTask<Integer, Void, Void> {
        private GameDao gameDao;

        updateBacklogAsyncTask(GameDao dao) { gameDao = dao; }

        @Override
        protected Void doInBackground(final Integer... params) {
            gameDao.updateLaterBacklog(params[0]);
            return null;
        }
    }
}
