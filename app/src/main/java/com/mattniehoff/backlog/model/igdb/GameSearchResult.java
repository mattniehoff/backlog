package com.mattniehoff.backlog.model.igdb;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Generated with http://www.jsonschema2pojo.org/
public class GameSearchResult implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    public final static Parcelable.Creator<GameSearchResult> CREATOR = new Creator<GameSearchResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GameSearchResult createFromParcel(Parcel in) {
            return new GameSearchResult(in);
        }

        public GameSearchResult[] newArray(int size) {
            return (new GameSearchResult[size]);
        }

    };

    protected GameSearchResult(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public GameSearchResult() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}