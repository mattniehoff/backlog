package com.mattniehoff.backlog.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mattniehoff.backlog.model.igdb.GameSearchResult;
import com.mattniehoff.backlog.repository.GameRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {
    private final GameRepository repository;
    //private LiveData<List<GameSearchResult>> searchResults;
//    private MutableLiveData<List<GameSearchResult>> searchResults = MutableLiveData();
//
    public SearchViewModel(GameRepository repository) {
        this.repository = repository;
      //  this.searchResults = repository.searchGamesByQueryString("");
    }
//
//    public void searchByQueryString(String queryString) {
//        this.searchResults = repository.searchGamesByQueryString(queryString);
//    }
//
//    public LiveData<List<GameSearchResult>> getSearchResults() {
//        return searchResults;
//    }

    private LiveData<List<GameSearchResult>> searchResults;

    public void init(String queryString) {
        if (this.searchResults != null) {
            return;
        }

        this.searchResults = repository.searchGamesByQueryString(queryString);
    }

    public LiveData<List<GameSearchResult>> getSearchResults() {
        return this.searchResults;
    }
}
