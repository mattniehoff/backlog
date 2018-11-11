
package com.mattniehoff.backlog.model.igdb;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Esrb implements Parcelable {

    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    public final static Parcelable.Creator<Esrb> CREATOR = new Creator<Esrb>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Esrb createFromParcel(Parcel in) {
            return new Esrb(in);
        }

        public Esrb[] newArray(int size) {
            return (new Esrb[size]);
        }

    };

    protected Esrb(Parcel in) {
        this.synopsis = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Esrb() {
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(synopsis);
        dest.writeValue(rating);
    }

    public int describeContents() {
        return 0;
    }

}
