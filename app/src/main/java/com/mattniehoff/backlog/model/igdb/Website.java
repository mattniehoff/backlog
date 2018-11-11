
package com.mattniehoff.backlog.model.igdb;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Website implements Parcelable {

    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("url")
    @Expose
    private String url;
    public final static Parcelable.Creator<Website> CREATOR = new Creator<Website>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Website createFromParcel(Parcel in) {
            return new Website(in);
        }

        public Website[] newArray(int size) {
            return (new Website[size]);
        }

    };

    protected Website(Parcel in) {
        this.category = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Website() {
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(category);
        dest.writeValue(url);
    }

    public int describeContents() {
        return 0;
    }

}
