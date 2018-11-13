
package com.mattniehoff.backlog.model.igdb;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameDetail implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("created_at")
    @Expose
    private Long createdAt;
    @SerializedName("updated_at")
    @Expose
    private Long updatedAt;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("collection")
    @Expose
    private Integer collection;
    @SerializedName("franchise")
    @Expose
    private Integer franchise;
    @SerializedName("franchises")
    @Expose
    private List<Integer> franchises = null;
    @SerializedName("hypes")
    @Expose
    private Integer hypes;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("aggregated_rating")
    @Expose
    private Double aggregatedRating;
    @SerializedName("aggregated_rating_count")
    @Expose
    private Integer aggregatedRatingCount;
    @SerializedName("total_rating")
    @Expose
    private Double totalRating;
    @SerializedName("total_rating_count")
    @Expose
    private Integer totalRatingCount;
    @SerializedName("rating_count")
    @Expose
    private Integer ratingCount;
    @SerializedName("games")
    @Expose
    private List<Integer> games = null;
    @SerializedName("tags")
    @Expose
    private List<Integer> tags = null;
    @SerializedName("developers")
    @Expose
    private List<Integer> developers = null;
    @SerializedName("publishers")
    @Expose
    private List<Integer> publishers = null;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("player_perspectives")
    @Expose
    private List<Integer> playerPerspectives = null;
    @SerializedName("game_modes")
    @Expose
    private List<Integer> gameModes = null;
    @SerializedName("keywords")
    @Expose
    private List<Integer> keywords = null;
    @SerializedName("themes")
    @Expose
    private List<Integer> themes = null;
    @SerializedName("genres")
    @Expose
    private List<Integer> genres = null;
    @SerializedName("first_release_date")
    @Expose
    private Long firstReleaseDate;
    @SerializedName("pulse_count")
    @Expose
    private Integer pulseCount;
    @SerializedName("platforms")
    @Expose
    private List<Integer> platforms = null;
    @SerializedName("release_dates")
    @Expose
    private List<ReleaseDate> releaseDates = null;
    @SerializedName("screenshots")
    @Expose
    private List<Screenshot> screenshots = null;
    @SerializedName("artworks")
    @Expose
    private List<Artwork> artworks = null;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("esrb")
    @Expose
    private Esrb esrb;
    @SerializedName("websites")
    @Expose
    private List<Website> websites = null;
    @SerializedName("external")
    @Expose
    private External external;
    public final static Parcelable.Creator<GameDetail> CREATOR = new Creator<GameDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GameDetail createFromParcel(Parcel in) {
            return new GameDetail(in);
        }

        public GameDetail[] newArray(int size) {
            return (new GameDetail[size]);
        }

    };

    protected GameDetail(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.slug = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((Long) in.readValue((Long.class.getClassLoader())));
        this.updatedAt = ((Long) in.readValue((Long.class.getClassLoader())));
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.collection = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.franchise = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.franchises, (java.lang.Integer.class.getClassLoader()));
        this.hypes = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.rating = ((Double) in.readValue((Double.class.getClassLoader())));
        this.popularity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.aggregatedRating = ((Double) in.readValue((Double.class.getClassLoader())));
        this.aggregatedRatingCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalRating = ((Double) in.readValue((Double.class.getClassLoader())));
        this.totalRatingCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.ratingCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.games, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.tags, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.developers, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.publishers, (java.lang.Integer.class.getClassLoader()));
        this.category = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.playerPerspectives, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.gameModes, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.keywords, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.themes, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.genres, (java.lang.Integer.class.getClassLoader()));
        this.firstReleaseDate = ((Long) in.readValue((Integer.class.getClassLoader())));
        this.pulseCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.platforms, (java.lang.Integer.class.getClassLoader()));
        in.readList(this.releaseDates, (com.mattniehoff.backlog.model.igdb.ReleaseDate.class.getClassLoader()));
        in.readList(this.screenshots, (com.mattniehoff.backlog.model.igdb.Screenshot.class.getClassLoader()));
        in.readList(this.artworks, (com.mattniehoff.backlog.model.igdb.Artwork.class.getClassLoader()));
        in.readList(this.videos, (com.mattniehoff.backlog.model.igdb.Video.class.getClassLoader()));
        this.cover = ((Cover) in.readValue((Cover.class.getClassLoader())));
        this.esrb = ((Esrb) in.readValue((Esrb.class.getClassLoader())));
        in.readList(this.websites, (com.mattniehoff.backlog.model.igdb.Website.class.getClassLoader()));
        this.external = ((External) in.readValue((External.class.getClassLoader())));
    }

    public GameDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Integer getFranchise() {
        return franchise;
    }

    public void setFranchise(Integer franchise) {
        this.franchise = franchise;
    }

    public List<Integer> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<Integer> franchises) {
        this.franchises = franchises;
    }

    public Integer getHypes() {
        return hypes;
    }

    public void setHypes(Integer hypes) {
        this.hypes = hypes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Double getAggregatedRating() {
        return aggregatedRating;
    }

    public void setAggregatedRating(Double aggregatedRating) {
        this.aggregatedRating = aggregatedRating;
    }

    public Integer getAggregatedRatingCount() {
        return aggregatedRatingCount;
    }

    public void setAggregatedRatingCount(Integer aggregatedRatingCount) {
        this.aggregatedRatingCount = aggregatedRatingCount;
    }

    public Double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Double totalRating) {
        this.totalRating = totalRating;
    }

    public Integer getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(Integer totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public List<Integer> getGames() {
        return games;
    }

    public void setGames(List<Integer> games) {
        this.games = games;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public List<Integer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Integer> developers) {
        this.developers = developers;
    }

    public List<Integer> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Integer> publishers) {
        this.publishers = publishers;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public List<Integer> getPlayerPerspectives() {
        return playerPerspectives;
    }

    public void setPlayerPerspectives(List<Integer> playerPerspectives) {
        this.playerPerspectives = playerPerspectives;
    }

    public List<Integer> getGameModes() {
        return gameModes;
    }

    public void setGameModes(List<Integer> gameModes) {
        this.gameModes = gameModes;
    }

    public List<Integer> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Integer> keywords) {
        this.keywords = keywords;
    }

    public List<Integer> getThemes() {
        return themes;
    }

    public void setThemes(List<Integer> themes) {
        this.themes = themes;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public Long getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(Long firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public Integer getPulseCount() {
        return pulseCount;
    }

    public void setPulseCount(Integer pulseCount) {
        this.pulseCount = pulseCount;
    }

    public List<Integer> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Integer> platforms) {
        this.platforms = platforms;
    }

    public List<ReleaseDate> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(List<ReleaseDate> releaseDates) {
        this.releaseDates = releaseDates;
    }

    public List<Screenshot> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<Screenshot> screenshots) {
        this.screenshots = screenshots;
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public Esrb getEsrb() {
        return esrb;
    }

    public void setEsrb(Esrb esrb) {
        this.esrb = esrb;
    }

    public List<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(List<Website> websites) {
        this.websites = websites;
    }

    public External getExternal() {
        return external;
    }

    public void setExternal(External external) {
        this.external = external;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(slug);
        dest.writeValue(url);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(summary);
        dest.writeValue(collection);
        dest.writeValue(franchise);
        dest.writeList(franchises);
        dest.writeValue(hypes);
        dest.writeValue(rating);
        dest.writeValue(popularity);
        dest.writeValue(aggregatedRating);
        dest.writeValue(aggregatedRatingCount);
        dest.writeValue(totalRating);
        dest.writeValue(totalRatingCount);
        dest.writeValue(ratingCount);
        dest.writeList(games);
        dest.writeList(tags);
        dest.writeList(developers);
        dest.writeList(publishers);
        dest.writeValue(category);
        dest.writeList(playerPerspectives);
        dest.writeList(gameModes);
        dest.writeList(keywords);
        dest.writeList(themes);
        dest.writeList(genres);
        dest.writeValue(firstReleaseDate);
        dest.writeValue(pulseCount);
        dest.writeList(platforms);
        dest.writeList(releaseDates);
        dest.writeList(screenshots);
        dest.writeList(artworks);
        dest.writeList(videos);
        dest.writeValue(cover);
        dest.writeValue(esrb);
        dest.writeList(websites);
        dest.writeValue(external);
    }

    public int describeContents() {
        return 0;
    }

    // API response is //www.address.jpg, this returns empty string if no value or null
    // and the http: prepended address if there is a value
    public String getCoverUrl() {
        if (cover != null) {
            return cover.getHttpUrl();
        }

        return "";
    }

}
