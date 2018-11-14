package com.mattniehoff.backlog.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mattniehoff.backlog.adapters.GameEntryOnItemClickHandler;
import com.mattniehoff.backlog.adapters.LibraryAdapter;
import com.mattniehoff.backlog.utils.InjectorUtils;
import com.mattniehoff.backlog.viewmodels.LibraryViewModel;
import com.mattniehoff.backlog.R;
import com.mattniehoff.backlog.viewmodels.LibraryViewModelFactory;

public class LibraryFragment extends Fragment {

    private static final String TAG = LibraryFragment.class.getSimpleName();
    private LibraryAdapter libraryAdapter;
    private RecyclerView recyclerView;
    private int position = RecyclerView.NO_POSITION;
    private LibraryViewModel libraryViewModel;

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.library_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.library_fragment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        libraryAdapter = new LibraryAdapter(getContext(), (GameEntryOnItemClickHandler)getActivity());
        recyclerView.setAdapter(libraryAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LibraryViewModelFactory factory = InjectorUtils.provideLibraryViewModelFactory(getActivity().getApplicationContext());
        libraryViewModel = ViewModelProviders.of(this, factory).get(LibraryViewModel.class);
        libraryViewModel.getGameLibrary().observe(this, gameLibrary -> {
            libraryAdapter.setGameEntryList(gameLibrary);
            if (position == RecyclerView.NO_POSITION) {
                position = 0;
            }
            recyclerView.smoothScrollToPosition(position);
        });
    }


}
