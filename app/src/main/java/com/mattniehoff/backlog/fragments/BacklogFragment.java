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

import com.mattniehoff.backlog.adapters.BacklogAdapter;
import com.mattniehoff.backlog.adapters.GameEntryOnItemClickHandler;
import com.mattniehoff.backlog.utils.InjectorUtils;
import com.mattniehoff.backlog.viewmodels.BacklogViewModel;
import com.mattniehoff.backlog.R;
import com.mattniehoff.backlog.viewmodels.BacklogViewModelFactory;

public class BacklogFragment extends Fragment {

    private static final String TAG = BacklogFragment.class.getSimpleName();
    private BacklogAdapter backlogAdapter;
    private RecyclerView recyclerView;
    private int position = RecyclerView.NO_POSITION;
    private BacklogViewModel backlogViewModel;

    public static BacklogFragment newInstance() {
        return new BacklogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.backlog_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.backlog_fragment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        backlogAdapter = new BacklogAdapter(getContext(), (GameEntryOnItemClickHandler)getActivity());
        recyclerView.setAdapter(backlogAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BacklogViewModelFactory factory = InjectorUtils.provideBacklogViewModelFactory(getActivity().getApplicationContext());
        backlogViewModel = ViewModelProviders.of(this, factory).get(BacklogViewModel.class);
        backlogViewModel.getGameBacklog().observe(this, gameLibrary -> {
            backlogAdapter.setGameEntryList(gameLibrary);
            if (position == RecyclerView.NO_POSITION) {
                position = 0;
            }
            recyclerView.smoothScrollToPosition(position);
        });
    }

}
