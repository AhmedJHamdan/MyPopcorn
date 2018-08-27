package com.udacity.ahmed.mypopcorn.pojo.Cast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cast implements Parcelable {


    public static final Creator<Cast> CREATOR = new Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel source) {
            return new Cast(source);
        }

        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("character")
    @Expose
    private String character;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("episode_count")
    @Expose
    private Integer episodeCount;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("original_name")
    @Expose
    private String originalName;

    public Cast() {
    }

    protected Cast(Parcel in) {
        this.gender = (Integer) in.readValue(Integer.class.getClassLoader());
        this.profilePath = in.readString();
        this.order = (Integer) in.readValue(Integer.class.getClassLoader());
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.character = in.readString();
        this.creditId = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originalTitle = in.readString();
        this.posterPath = in.readString();
        this.releaseDate = in.readString();
        this.title = in.readString();
        this.mediaType = in.readString();
        this.episodeCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.firstAirDate = in.readString();
        this.name = in.readString();
        this.originalName = in.readString();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.gender);
        dest.writeString(this.profilePath);
        dest.writeValue(this.order);
        dest.writeValue(this.adult);
        dest.writeString(this.character);
        dest.writeString(this.creditId);
        dest.writeValue(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.posterPath);
        dest.writeString(this.releaseDate);
        dest.writeString(this.title);
        dest.writeString(this.mediaType);
        dest.writeValue(this.episodeCount);
        dest.writeString(this.firstAirDate);
        dest.writeString(this.name);
        dest.writeString(this.originalName);
    }
}