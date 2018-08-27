package com.udacity.ahmed.mypopcorn.pojo.Picture;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Hamdan.
 */

public class Pictures implements Parcelable {


    public static final Creator<Pictures> CREATOR = new Creator<Pictures>() {
        @Override
        public Pictures createFromParcel(Parcel source) {
            return new Pictures(source);
        }

        @Override
        public Pictures[] newArray(int size) {
            return new Pictures[size];
        }
    };
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = new ArrayList<>();
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public Pictures() {
    }

    protected Pictures(Parcel in) {
        this.posterPath = in.readString();
        this.name = in.readString();
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * @param posterPath The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    /**
     * @return The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     * @param adult The adult
     */
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    /**
     * @return The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @param overview The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * @return The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return The genreIds
     */
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    /**
     * @param genreIds The genre_ids
     */
    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * @param originalTitle The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * @return The originalLanguage
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * @param originalLanguage The original_language
     */
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The backdropPath
     */
    public String getBackdropPath() {
        return backdropPath;
    }

    /**
     * @param backdropPath The backdrop_path
     */
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    /**
     * @return The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     * @param popularity The popularity
     */
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    /**
     * @return The voteCount
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     * @param voteCount The vote_count
     */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    /**
     * @return The video
     */
    public Boolean getVideo() {
        return video;
    }

    /**
     * @param video The video
     */
    public void setVideo(Boolean video) {
        this.video = video;
    }

    /**
     * @return The voteAverage
     */
    public Double getVoteAverage() {
        return voteAverage;
    }

    /**
     * @param voteAverage The vote_average
     */
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeString(this.name);
        dest.writeValue(this.adult);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeList(this.genreIds);
        dest.writeValue(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeValue(this.popularity);
        dest.writeValue(this.voteCount);
        dest.writeValue(this.video);
        dest.writeValue(this.voteAverage);
    }
}
