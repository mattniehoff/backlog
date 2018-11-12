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
import com.mattniehoff.backlog.viewmodels.LibraryViewModel;
import com.squareup.picasso.Picasso;

import java.util.Date;
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
        libraryViewHolder.gameIdTextView.setText("Game ID: " + gameEntry.getId());

        String gameCoverUrl = gameEntry.getCoverUrl();
        if (gameCoverUrl.length() > 0) {
            Picasso.get()
                    .load(gameCoverUrl)
//                    .placeholder(R.drawable.ic_videogame_asset_black_24dp)
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
        newGameEntryList.add(new GameEntry(1, "The first game", new Date(), null, "http://images.igdb.com/igdb/image/upload/t_thumb/f9jvrf3nwdgdil287sla.jpg"));
        newGameEntryList.add(new GameEntry(2, "The second game", new Date(), null, "images.igdb.com/igdb/image/upload/t_thumb/f9jvrf3nwdgdil287sla.jpg"));

        gameEntryList = newGameEntryList;
        notifyDataSetChanged();
    }

    class LibraryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView gameTitleTextView;
        final TextView gameIdTextView;
        final ImageView gameCoverImageView;

        LibraryViewHolder(View view) {
            super(view);

            gameTitleTextView = view.findViewById(R.id.library_list_item_game_title_text_view);
            gameIdTextView = view.findViewById(R.id.library_list_item_game_id_text_view);
            gameCoverImageView = view.findViewById(R.id.library_list_item_cover_image_view);

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
