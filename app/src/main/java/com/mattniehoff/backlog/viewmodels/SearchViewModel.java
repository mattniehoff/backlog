package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.mattniehoff.backlog.model.igdb.GameSearchResult;
import com.mattniehoff.backlog.repository.GameRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {
    private final GameRepository repository;

    private LiveData<List<GameSearchResult>> searchResults;
    private MutableLiveData<String> query = new MutableLiveData<>();

    // https://stackoverflow.com/a/48047182/2107568
    public SearchViewModel(GameRepository repository) {
        this.repository = repository;
        this.searchResults = Transformations.switchMap(query, (search) -> {
            if (search == null || search.trim().length() == 0) {
                return repository.searchGamesByQueryString("");
            } else {
                return repository.searchGamesByQueryString(search);
            }
        });

        runQuery("");
    }

    public void runQuery(String queryString) {
        query.setValue(queryString);
    }

    public LiveData<List<GameSearchResult>> getSearchResults() {
        return this.searchResults;
    }
}
