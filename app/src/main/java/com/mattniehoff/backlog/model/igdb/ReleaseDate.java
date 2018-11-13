
package com.mattniehoff.backlog.model.igdb;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReleaseDate implements Parcelable {

    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("platform")
    @Expose
    private Integer platform;
    @SerializedName("date")
    @Expose
    private Long date;
    @SerializedName("region")
    @Expose
    private Integer region;
    @SerializedName("human")
    @Expose
    private String human;
    @SerializedName("y")
    @Expose
    private Integer y;
    @SerializedName("m")
    @Expose
    private Integer m;
    public final static Parcelable.Creator<ReleaseDate> CREATOR = new Creator<ReleaseDate>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReleaseDate createFromParcel(Parcel in) {
            return new ReleaseDate(in);
        }

        public ReleaseDate[] newArray(int size) {
            return (new ReleaseDate[size]);
        }

    };

    protected ReleaseDate(Parcel in) {
        this.category = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.platform = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.date = ((Long) in.readValue((Long.class.getClassLoader())));
        this.region = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.human = ((String) in.readValue((String.class.getClassLoader())));
        this.y = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.m = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ReleaseDate() {
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getHuman() {
        return human;
    }

    public void setHuman(String human) {
        this.human = human;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(category);
        dest.writeValue(platform);
        dest.writeValue(date);
        dest.writeValue(region);
        dest.writeValue(human);
        dest.writeValue(y);
        dest.writeValue(m);
    }

    public int describeContents() {
        return 0;
    }

}
