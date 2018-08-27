package com.udacity.ahmed.mypopcorn.pojo.Images;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed Hamdan.
 */

public class Backdrop implements Parcelable {

    public static final Creator<Backdrop> CREATOR = new Creator<Backdrop>() {
        @Override
        public Backdrop createFromParcel(Parcel source) {
            return new Backdrop(source);
        }

        @Override
        public Backdrop[] newArray(int size) {
            return new Backdrop[size];
        }
    };
    @SerializedName("aspect_ratio")
    @Expose
    private Double aspectRatio;
    @SerializedName("file_path")
    @Expose
    private String filePath;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("width")
    @Expose
    private Integer width;

    public Backdrop() {
    }

    protected Backdrop(Parcel in) {
        this.aspectRatio = (Double) in.readValue(Double.class.getClassLoader());
        this.filePath = in.readString();
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.iso6391 = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.aspectRatio);
        dest.writeString(this.filePath);
        dest.writeValue(this.height);
        dest.writeString(this.iso6391);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.voteCount);
        dest.writeValue(this.width);
    }
}
