package com.mattniehoff.backlog.model.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.mattniehoff.backlog.model.igdb.GameDetail;

import java.util.Date;

@Entity(tableName = "game_entry")
public class GameEntry {

    @PrimaryKey
    private Integer id;

    @NonNull
    private String name;

    private String summary;

    @NonNull
    private Date dateAdded;

    private Date dateCompleted;

    private Integer backlogPriority;

    private String coverImageHash;

    private String headerImageHash;

    public GameEntry(Integer id, @NonNull String name, String summary, @NonNull Date dateAdded, Date dateCompleted, Integer backlogPriority, String coverImageHash, String headerImageHash) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.dateAdded = dateAdded;
        this.dateCompleted = dateCompleted;
        this.backlogPriority = backlogPriority;
        this.coverImageHash = coverImageHash;
        this.headerImageHash = headerImageHash;
    }

    // Constructor for game entry that takes a GameDetail.
    // This is useful for turning API results into database-savable entities
    public GameEntry(GameDetail detail) {
        this.id = detail.getId();
        this.name = detail.getName();
        this.summary = detail.getSummary();
        this.dateAdded = new Date();
        this.dateCompleted = null;
        this.backlogPriority = null;
        this.coverImageHash = detail.getCoverImageHash();
        this.headerImageHash = detail.getHeaderImageHash();
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

    public String getCoverImageHash() {
        return coverImageHash;
    }

    public void setCoverImageHash(String coverImageHash) {
        this.coverImageHash = coverImageHash;
    }

    public String getHeaderImageHash() {
        return headerImageHash;
    }

    public void setHeaderImageHash(String headerImageHash) {
        this.headerImageHash = headerImageHash;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
