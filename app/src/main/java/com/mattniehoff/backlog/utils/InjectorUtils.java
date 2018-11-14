package com.mattniehoff.backlog.utils;

import android.content.Context;

import com.mattniehoff.backlog.AppExecutors;
import com.mattniehoff.backlog.model.database.AppDatabase;
import com.mattniehoff.backlog.repository.GameRepository;
import com.mattniehoff.backlog.retrofit.IgdbGameService;
import com.mattniehoff.backlog.retrofit.IgdbGamesClient;
import com.mattniehoff.backlog.viewmodels.BacklogViewModelFactory;
import com.mattniehoff.backlog.viewmodels.GameDetailViewModelFactory;
import com.mattniehoff.backlog.viewmodels.LibraryViewModelFactory;
import com.mattniehoff.backlog.viewmodels.SearchViewModelFactory;
import com.mattniehoff.backlog.viewmodels.StatisticsViewModelFactory;

// Resources:
// https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html?index=..%2F..%2Findex#8
public class InjectorUtils {
    public static GameRepository provideRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        IgdbGamesClient client = IgdbGameService.getApiClient();
        AppExecutors executors = AppExecutors.getInstance();
        return GameRepository.getInstance(database.gameDao(), client, executors);
    }

    public static GameDetailViewModelFactory provideGameDetailViewModelFactory(Context context, int gameId) {
        GameRepository repository = provideRepository(context.getApplicationContext());
        return new GameDetailViewModelFactory(repository, gameId);
    }

    public static LibraryViewModelFactory provideLibraryViewModelFactory(Context context) {
        GameRepository repository = provideRepository(context.getApplicationContext());
        return new LibraryViewModelFactory(repository);
    }

    public static BacklogViewModelFactory provideBacklogViewModelFactory(Context context) {
        GameRepository repository = provideRepository(context.getApplicationContext());
        return new BacklogViewModelFactory(repository);
    }

    public static SearchViewModelFactory provideSearchViewModelFactory(Context context) {
        GameRepository repository = provideRepository(context.getApplicationContext());
        return new SearchViewModelFactory(repository);
    }

    public static StatisticsViewModelFactory provideStatisticsViewModelFactory(Context context) {
        GameRepository repository = provideRepository(context.getApplicationContext());
        return new StatisticsViewModelFactory(repository);
    }
}
