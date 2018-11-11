
package com.mattniehoff.backlog.model.igdb;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class External implements Parcelable {

    @SerializedName("steam")
    @Expose
    private String steam;
    public final static Parcelable.Creator<External> CREATOR = new Creator<External>() {


        @SuppressWarnings({
                "unchecked"
        })
        public External createFromParcel(Parcel in) {
            return new External(in);
        }

        public External[] newArray(int size) {
            return (new External[size]);
        }

    };

    protected External(Parcel in) {
        this.steam = ((String) in.readValue((String.class.getClassLoader())));
    }

    public External() {
    }

    public String getSteam() {
        return steam;
    }

    public void setSteam(String steam) {
        this.steam = steam;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(steam);
    }

    public int describeContents() {
        return 0;
    }

}
