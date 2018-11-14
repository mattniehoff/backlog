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

import java.util.Date;
import java.util.List;

public class BacklogAdapter extends RecyclerView.Adapter<BacklogAdapter.BacklogViewHolder> {

    private List<GameEntry> gameEntryList;

    private final Context context;
    private final GameEntryOnItemClickHandler clickHandler;

    public BacklogAdapter(@NonNull Context context, GameEntryOnItemClickHandler clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public BacklogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.backlog_list_item, viewGroup, false);
        return new BacklogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BacklogViewHolder backlogViewHolder, int position) {
        GameEntry gameEntry = gameEntryList.get(position);

        backlogViewHolder.gameTitleTextView.setText(gameEntry.getName());
        backlogViewHolder.gameIdTextView.setText("Game ID: " + gameEntry.getId());

        String gameCoverUrl = IgdbImageUtils.generateImageUrl(gameEntry.getCoverImageHash(), IgdbImageSize.thumb);
        if (gameCoverUrl.length() > 0) {
            Picasso.get()
                    .load(gameCoverUrl)
                    .error(R.drawable.ic_videogame_asset_black_24dp)
                    .into(backlogViewHolder.gameCoverImageView);
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
        newGameEntryList.add(new GameEntry(1, "The first game", new Date(), null, "f9jvrf3nwdgdil287sla", "kpnxx0mtm8iv1041h67t"));
        newGameEntryList.add(new GameEntry(2, "The second game", new Date(), null, "f9jvrf3nwdgdil287sla", "fkkiiyjtcu7os0fpvhe0"));

        gameEntryList = newGameEntryList;
        notifyDataSetChanged();
    }

    class BacklogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView gameTitleTextView;
        final TextView gameIdTextView;
        final ImageView gameCoverImageView;

        BacklogViewHolder(View view) {
            super(view);

            gameTitleTextView = view.findViewById(R.id.backlog_list_item_game_title_text_view);
            gameIdTextView = view.findViewById(R.id.backlog_list_item_game_id_text_view);
            gameCoverImageView = view.findViewById(R.id.backlog_list_item_cover_image_view);

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
