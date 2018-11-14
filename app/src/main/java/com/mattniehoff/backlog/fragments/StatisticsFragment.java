package com.mattniehoff.backlog.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mattniehoff.backlog.R;
import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.utils.InjectorUtils;
import com.mattniehoff.backlog.viewmodels.StatisticsViewModel;
import com.mattniehoff.backlog.viewmodels.StatisticsViewModelFactory;

import java.util.List;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel statisticsViewModel;

    private TextView gamesTotalTextView;
    private TextView completeTotalTextView;
    private TextView backlogTotalTextView;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statistics_fragment, container, false);

        gamesTotalTextView = rootView.findViewById(R.id.stats_games_total_text_view);
        completeTotalTextView = rootView.findViewById(R.id.stats_complete_total_text_view);
        backlogTotalTextView = rootView.findViewById(R.id.stats_backlog_total_text_view);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatisticsViewModelFactory factory = InjectorUtils.provideStatisticsViewModelFactory(getActivity().getApplicationContext());
        statisticsViewModel = ViewModelProviders.of(this, factory).get(StatisticsViewModel.class);
        statisticsViewModel.getGameLiibrary().observe(this, gameLibrary -> {
            updateStatisticsUi(gameLibrary);
        });
    }

    private void updateStatisticsUi(List<GameEntry> gameLibrary) {
        int totalGames = gameLibrary.size();
        int completeGames = 0;
        int backlogGames = 0;

        for (GameEntry entry : gameLibrary) {
            if (entry.getBacklogPriority() != null) {
                backlogGames++;
            }

            if (entry.getDateCompleted() != null) {
                completeGames++;
            }
        }

        setListMetrics(totalGames, completeGames, backlogGames);
    }

    private void setListMetrics(int totalGames, int completeGames, int backlogGames) {
        gamesTotalTextView.setText(String.valueOf(totalGames));

        int completePercentage = 0;
        if (totalGames > 0) {
            completePercentage = (completeGames * 100) / totalGames;
        }

        completeTotalTextView.setText(String.format(getString(R.string.complete_games_percent_string), completeGames, completePercentage));

        backlogTotalTextView.setText(String.valueOf(backlogGames));
    }
}
