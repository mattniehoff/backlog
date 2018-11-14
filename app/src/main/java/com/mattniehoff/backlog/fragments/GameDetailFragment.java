package com.mattniehoff.backlog.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.model.igdb.GameDetail;
import com.mattniehoff.backlog.model.igdb.IgdbImageSize;
import com.mattniehoff.backlog.utils.IgdbImageUtils;
import com.mattniehoff.backlog.utils.InjectorUtils;
import com.mattniehoff.backlog.viewmodels.GameDetailViewModel;
import com.mattniehoff.backlog.R;
import com.mattniehoff.backlog.viewmodels.GameDetailViewModelFactory;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class GameDetailFragment extends Fragment {

    // argument representing item ID for the fragment
    public static final String GAME_ID = "game_id";

    private GameDetailViewModel gameDetailViewModel;
    private int gameId;

    private TextView titleTextView;
    private TextView summaryTextView;

    private ImageView headerImageView;
    private ImageView coverImageView;

    private Button toggleLibraryButton;
    private TextView libraryTextView;

    private Button toggleBacklogButton;
    private TextView backlogTextView;

    private Button toggleCompleteButton;
    private TextView completeTextView;

    private Boolean isInLibrary = false;
    private Boolean isInBacklog = false;

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

        titleTextView = rootView.findViewById(R.id.game_detail_title_text_view);
        summaryTextView = rootView.findViewById(R.id.game_detail_summary_text_view);

        headerImageView = rootView.findViewById(R.id.game_detail_header_image);
        coverImageView = rootView.findViewById(R.id.game_detail_cover_image_view);

        libraryTextView = rootView.findViewById(R.id.game_detail_library_text_view);
        toggleLibraryButton = rootView.findViewById(R.id.game_detail_button_library);
        toggleLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInLibrary) {
                    gameDetailViewModel.removeGameFromLibrary();
                } else {
                    gameDetailViewModel.saveNewGame();
                }
            }
        });

        backlogTextView = rootView.findViewById(R.id.game_detail_backlog_text_view);
        toggleBacklogButton = rootView.findViewById(R.id.game_detail_button_backlog);
        toggleBacklogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInBacklog) {
                    gameDetailViewModel.removeFromBacklog();
                } else {
                    gameDetailViewModel.addToBacklog();
                }
            }
        });

        completeTextView = rootView.findViewById(R.id.game_detail_complete_text_view);
        toggleCompleteButton = rootView.findViewById(R.id.game_detail_button_complete);
        toggleCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showCompletedMessage(new Date());
                if (!isInLibrary) {
                    if (!gameDetailViewModel.saveNewCompletedGame()) {
                        displayFailedToSaveMessage();
                    }
                } else {
                    gameDetailViewModel.markComplete();
                }
            }
        });



        return rootView;
    }

    private void displayFailedToSaveMessage() {
        Toast.makeText(getContext(), "Currently unable to save game. May be offline.", Toast.LENGTH_LONG).show();
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

        if (gameDetail != null) {
            titleTextView.setText(gameDetail.getName());
            summaryTextView.setText(gameDetail.getSummary());

            String gameCoverUrl = IgdbImageUtils.generateImageUrl(gameDetail.getHeaderImageHash(), IgdbImageSize.screenshot_med);
            if (gameCoverUrl.length() > 0) {
                Picasso.get()
                        .load(gameCoverUrl)
                        .error(R.drawable.ic_videogame_asset_black_24dp)
                        .into(headerImageView);
            }

            String coverImageUrl = IgdbImageUtils.generateImageUrl(gameDetail.getCoverImageHash(), IgdbImageSize.thumb);
            if (!coverImageUrl.isEmpty()) {
                coverImageView.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load(coverImageUrl)
                        .into(coverImageView, new Callback() {
                            // https://stackoverflow.com/a/38620591/2107568
                            // Don't want to show anything over if we don't have an image to load
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                coverImageView.setVisibility(View.GONE);
                            }
                        });
            }
        }
    }

    private void updateGameEntryUi(GameEntry gameEntry) {
        if (gameEntry != null) {
            isInLibrary = true;
            updateLibraryButtonUi(isInLibrary, gameEntry);


            if (gameEntry.getBacklogPriority() != null) {
                isInBacklog = true;
                toggleBacklogButton.setVisibility(View.GONE);
                backlogTextView.setText(String.format("%s%s", getString(R.string.backlog_position_message_prefix), gameEntry.getBacklogPriority().toString()));
                toggleVisibility(backlogTextView);
            }

            if (gameEntry.getDateCompleted() != null) {
                showCompletedMessage(gameEntry.getDateCompleted());
            }
        }
    }

    private void updateLibraryButtonUi(Boolean isInLibrary, GameEntry gameEntry) {
        if (isInLibrary) {
            toggleLibraryButton.setVisibility(View.GONE);
            libraryTextView.setText(String.format("%s%s", getString(R.string.library_message_prefix), gameEntry.getDateAdded().toString()));
            libraryTextView.setVisibility(View.VISIBLE);

        } else {
            toggleLibraryButton.setVisibility(View.VISIBLE);
            libraryTextView.setText("");
            libraryTextView.setVisibility(View.GONE);
        }
    }

    private void toggleVisibility(TextView textView) {
        if (textView.getVisibility() == View.GONE) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    // Method hides other buttons and shows date completed message.
    private void showCompletedMessage(Date dateCompleted) {
        toggleLibraryButton.setVisibility(View.GONE);
        toggleBacklogButton.setVisibility(View.GONE);
        toggleCompleteButton.setVisibility(View.GONE);
        completeTextView.setVisibility(View.VISIBLE);
        completeTextView.setText(String.format("%s%s", getString(R.string.date_complete_message_prefix), dateCompleted.toString()));
    }

}
