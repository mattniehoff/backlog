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
import com.mattniehoff.backlog.model.igdb.GameSearchResult;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<GameEntry> gameSearchResults;

    private final Context context;
    private final GameEntryOnItemClickHandler clickHandler;

    public SearchAdapter(@NonNull Context context, GameEntryOnItemClickHandler clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item, viewGroup, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder searchViewHolder, int position) {
        GameEntry gameSearchResult = gameSearchResults.get(position);

        searchViewHolder.gameTitleTextView.setText(gameSearchResult.getName());
        searchViewHolder.gameIdTextView.setText("Game ID: " + gameSearchResult.getId());

        String gameCoverUrl = gameSearchResult.getCoverUrl();
        if (gameCoverUrl.length() > 0) {
            Picasso.get()
                    .load(gameCoverUrl)
                    .error(R.drawable.ic_videogame_asset_black_24dp)
                    .into(searchViewHolder.gameCoverImageView);
        }
    }

    @Override
    public int getItemCount() {
        if (gameSearchResults == null) {
            return 0;
        }

        return gameSearchResults.size();
    }

    public void setGameSearchResults(final List<GameEntry> newGameEntryList) {
//        newGameEntryList.add(new GameEntry(1, "The first game", new Date(), null, "http://images.igdb.com/igdb/image/upload/t_thumb/f9jvrf3nwdgdil287sla.jpg"));
//        newGameEntryList.add(new GameEntry(2, "The second game", new Date(), null, "images.igdb.com/igdb/image/upload/t_thumb/f9jvrf3nwdgdil287sla.jpg"));

        gameSearchResults = newGameEntryList;
        notifyDataSetChanged();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView gameTitleTextView;
        final TextView gameIdTextView;
        final ImageView gameCoverImageView;

        SearchViewHolder(View view) {
            super(view);

            gameTitleTextView = view.findViewById(R.id.search_list_item_game_title_text_view);
            gameIdTextView = view.findViewById(R.id.search_list_item_game_id_text_view);
            gameCoverImageView = view.findViewById(R.id.search_list_item_cover_image_view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            int gameId = gameSearchResults.get(adapterPosition).getId();
            clickHandler.onGameItemClick(gameId);
        }
    }
}
