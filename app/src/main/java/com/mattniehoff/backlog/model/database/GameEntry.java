package com.mattniehoff.backlog.model.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;
import android.support.annotation.NonNull;

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
}
