package com.mattniehoff.backlog.utils;

import android.content.Context;

import com.mattniehoff.backlog.AppExecutors;
import com.mattniehoff.backlog.model.database.AppDatabase;
import com.mattniehoff.backlog.repository.GameRepository;
import com.mattniehoff.backlog.viewmodels.GameDetailViewModelFactory;
import com.mattniehoff.backlog.viewmodels.LibraryViewModelFactory;

// Resources:
// https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html?index=..%2F..%2Findex#8
public class InjectorUtils {
    public static GameRepository provideRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return GameRepository.getInstance(database.gameDao(), executors);
    }

    public static GameDetailViewModelFactory provideGameDetailViewModelFactory(Context context, int gameId) {
        GameRepository repository = provideRepository(context.getApplicationContext());
        return new GameDetailViewModelFactory(repository, gameId);
    }

    public static LibraryViewModelFactory provideLibraryViewModelFactory(Context context) {
        GameRepository repository = provideRepository(context.getApplicationContext());
        return new LibraryViewModelFactory(repository);
    }
}
