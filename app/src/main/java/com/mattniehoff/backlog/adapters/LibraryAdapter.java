package com.mattniehoff.backlog.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mattniehoff.backlog.R;
import com.mattniehoff.backlog.model.database.GameEntry;
import com.mattniehoff.backlog.model.igdb.IgdbImageSize;
import com.mattniehoff.backlog.utils.IgdbImageUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder> {

    private List<GameEntry> gameEntryList;

    private final Context context;
    private final GameEntryOnItemClickHandler clickHandler;

    public LibraryAdapter(@NonNull Context context, GameEntryOnItemClickHandler clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.library_list_item, viewGroup, false);
        return new LibraryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder libraryViewHolder, int position) {
        GameEntry gameEntry = gameEntryList.get(position);

        libraryViewHolder.gameTitleTextView.setText(gameEntry.getName());

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateOnlyString = formatter.format(gameEntry.getDateAdded());

        libraryViewHolder.dateAddedTextView.setText(String.format("%s %s", context.getString(R.string.date_added_prefix), dateOnlyString));

        if (gameEntry.getDateCompleted() != null) {
            libraryViewHolder.checkmarkCompletedImageView.setVisibility(View.VISIBLE);
            libraryViewHolder.dateCompletedTextView.setVisibility(View.VISIBLE);

            dateOnlyString = formatter.format(gameEntry.getDateCompleted());
            libraryViewHolder.dateCompletedTextView.setText(String.format("%s %s", context.getString(R.string.date_complete_prefix), dateOnlyString));
        }

        String gameCoverUrl = IgdbImageUtils.generateImageUrl(gameEntry.getCoverImageHash(), IgdbImageSize.thumb);
        if (gameCoverUrl.length() > 0) {
            Picasso.get()
                    .load(gameCoverUrl)
                    .error(R.drawable.ic_videogame_asset_black_24dp)
                    .into(libraryViewHolder.gameCoverImageView);
        }
    }

    @Override
    public int getItemCount() {
        if (gameEntryList == null) {
            return 0;
        }

        return gameEntryList.size();
    }

    public void setGameEntryList(final List<GameEntry> newGameEntryList) {
        gameEntryList = newGameEntryList;
        notifyDataSetChanged();
    }

    class LibraryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView gameTitleTextView;
        final TextView dateAddedTextView;
        final ImageView gameCoverImageView;
        final TextView dateCompletedTextView;
        final ImageView checkmarkCompletedImageView;

        LibraryViewHolder(View view) {
            super(view);

            gameTitleTextView = view.findViewById(R.id.library_list_item_game_title_text_view);
            dateAddedTextView = view.findViewById(R.id.library_list_item_game_added_text_view);
            gameCoverImageView = view.findViewById(R.id.library_list_item_cover_image_view);

            dateCompletedTextView = view.findViewById(R.id.library_list_item_game_completed_text_view);
            checkmarkCompletedImageView = view.findViewById(R.id.library_list_completed_check_box);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            int gameId = gameEntryList.get(adapterPosition).getId();
            clickHandler.onGameItemClick(gameId);
        }
    }
}
