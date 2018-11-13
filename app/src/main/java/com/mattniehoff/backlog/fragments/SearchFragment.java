package com.mattniehoff.backlog.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mattniehoff.backlog.R;
import com.mattniehoff.backlog.adapters.GameEntryOnItemClickHandler;
import com.mattniehoff.backlog.adapters.SearchAdapter;
import com.mattniehoff.backlog.utils.InjectorUtils;
import com.mattniehoff.backlog.viewmodels.SearchViewModel;
import com.mattniehoff.backlog.viewmodels.SearchViewModelFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchFragment extends Fragment {

    private static final String TAG = SearchFragment.class.getSimpleName();
    private SearchViewModel searchViewModel;
    private EditText editText;
    private ImageButton searchImageButton;

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private int position = RecyclerView.NO_POSITION;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);

        setupImageSearchButton(rootView);
        setupEditText(rootView);

        recyclerView = rootView.findViewById(R.id.search_fragment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        searchAdapter = new SearchAdapter(getContext(), (GameEntryOnItemClickHandler)getActivity());
        recyclerView.setAdapter(searchAdapter);

        return rootView;
    }

    private void setupEditText(View rootView) {
        // https://stackoverflow.com/a/19217624/2107568
        editText = rootView.findViewById(R.id.search_edittext);
        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    searchImageButton.performClick();

                    // http://tonylukasavage.com/blog/2011/06/02/android-quick-tip--edittext-with-done-button-that-closes-the-keyboard/
                    InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    return true;
                }

                return false;
            }
        });
    }

    private void setupImageSearchButton(View rootView) {
        searchImageButton = rootView.findViewById(R.id.search_image_button);
        searchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encodedText;
                try {
                    encodedText = URLEncoder.encode(editText.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Log.e(TAG, "UnsupportedEncodingException:  " + e.toString());
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Failed to encode" + editText.getText(), Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getContext(), "Search for " + encodedText, Toast.LENGTH_LONG).show();
                searchViewModel.runQuery(encodedText);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SearchViewModelFactory factory = InjectorUtils.provideSearchViewModelFactory(getActivity().getApplicationContext());
        searchViewModel = ViewModelProviders.of(this, factory).get(SearchViewModel.class);

        // Run empty query so we can still be ob
        searchViewModel.getSearchResults().observe(getViewLifecycleOwner(), gameSearchResults -> {
            searchAdapter.setGameSearchResults(gameSearchResults);
            if (position == RecyclerView.NO_POSITION) {
                position = 0;
            }
            recyclerView.smoothScrollToPosition(position);
        });

    }

}
