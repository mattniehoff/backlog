package com.mattniehoff.backlog.model.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.mattniehoff.backlog.model.igdb.GameDetail;

import java.util.Date;

@Entity(tableName = "game_entry")
public class GameEntry {

    @PrimaryKey
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Date dateAdded;

    private Integer backlogPriority;

    private String coverUrl;

    public GameEntry(Integer id, @NonNull String name, @NonNull Date dateAdded, Integer backlogPriority, String coverUrl) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.backlogPriority = backlogPriority;
        this.coverUrl = coverUrl;
    }

    // Constructor for game entry that takes a GameDetail.
    // This is useful for turning API results into database-savable entities
    public GameEntry(GameDetail detail) {
        this.id = detail.getId();
        this.name = detail.getName();
        this.dateAdded = new Date();
        this.backlogPriority = null;
        this.coverUrl = detail.getCoverUrl();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(@NonNull Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Integer getBacklogPriority() {
        return backlogPriority;
    }

    public void setBacklogPriority(Integer backlogPriority) {
        this.backlogPriority = backlogPriority;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
