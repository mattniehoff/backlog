package com.mattniehoff.backlog.model.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GameDao {
    @Query("SELECT * FROM game_entry")
    LiveData<List<GameEntry>> getAllGames();

    @Query("SELECT * FROM game_entry WHERE id = :gameId")
    LiveData<GameEntry> getGameById(int gameId);

    @Query("SELECT * FROM game_entry WHERE backlogPriority is not null ORDER BY backlogPriority")
    LiveData<List<GameEntry>> getAllBacklogGames();

    @Query("UPDATE game_entry SET backlogPriority = backlogPriority - 1 WHERE backlogPriority > :priority")
    void updateLaterBacklog(int priority);

    @Query("SELECT COUNT(*) FROM game_entry WHERE backlogPriority is not null")
    LiveData<Integer> getBacklogCount();

    @Query("SELECT COUNT(*) FROM game_entry")
    LiveData<Integer> getRowCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGame(GameEntry gameEntry);

    @Insert
    void insertGame(GameEntry... gameEntries);

    @Update
    void updateGames(GameEntry... gameEntries);

    @Delete
    void deleteGame(GameEntry gameEntry);

    @Delete
    void deleteGames(GameEntry... gameEntries);

    @Query("DELETE FROM game_entry WHERE id = :gameId")
    void deleteGameById(int gameId);


}
