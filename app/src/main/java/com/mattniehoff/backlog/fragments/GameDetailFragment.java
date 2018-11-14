package com.mattniehoff.backlog.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.model.igdb.GameDetail;
import com.mattniehoff.backlog.model.igdb.IgdbImageSize;
import com.mattniehoff.backlog.utils.IgdbImageUtils;
import com.mattniehoff.backlog.utils.InjectorUtils;
import com.mattniehoff.backlog.viewmodels.GameDetailViewModel;
import com.mattniehoff.backlog.R;
import com.mattniehoff.backlog.viewmodels.GameDetailViewModelFactory;
import com.squareup.picasso.Picasso;

public class GameDetailFragment extends Fragment {

    // argument representing item ID for the fragment
    public static final String GAME_ID = "game_id";

    private GameDetailViewModel gameDetailViewModel;
    private int gameId;

    private ImageView headerImageView;

    public static GameDetailFragment newInstance() {
        return new GameDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(GAME_ID)) {
            gameId = getArguments().getInt(GAME_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.game_detail_fragment, container, false);

        headerImageView = rootView.findViewById(R.id.game_detail_header_image);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GameDetailViewModelFactory factory = InjectorUtils.provideGameDetailViewModelFactory(getActivity().getApplicationContext(), gameId);
        gameDetailViewModel = ViewModelProviders.of(this, factory).get(GameDetailViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        // Observe call to get game details
        gameDetailViewModel.getGameDetails().observe(getViewLifecycleOwner(), gameDetail -> {
            updateGameDetailUi(gameDetail);
        });

        // Observe call to database entry
        gameDetailViewModel.getGameEntry().observe(getViewLifecycleOwner(), gameEntry -> {
            updateGameEntryUi(gameEntry);
        });
    }

    private void updateGameDetailUi(GameDetail gameDetail) {
        String gameCoverUrl = IgdbImageUtils.generateImageUrl(gameDetail.getHeaderImageHash(), IgdbImageSize.screenshot_med);
        if (gameCoverUrl.length() > 0) {
            Picasso.get()
                    .load(gameCoverUrl)
                    .error(R.drawable.ic_videogame_asset_black_24dp)
                    .into(headerImageView);
        }
    }

    private void updateGameEntryUi(GameEntry gameEntry) {

    }

}
