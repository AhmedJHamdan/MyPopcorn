package com.udacity.ahmed.mypopcorn.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import com.udacity.ahmed.mypopcorn.pojo.Picture.Pictures;


/**
 * Created by Ahmed Hamdan.
 */

public class Similar implements Parcelable {

    public static final Creator<Similar> CREATOR = new Creator<Similar>() {
        @Override
        public Similar createFromParcel(Parcel source) {
            return new Similar(source);
        }

        @Override
        public Similar[] newArray(int size) {
            return new Similar[size];
        }
    };
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Pictures> results = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public Similar() {
    }

    protected Similar(Parcel in) {
        this.page = (Integer) in.readValue(Integer.class.getClassLoader());
        this.results = new ArrayList<>();
        in.readList(this.results, Pictures.class.getClassLoader());
        this.totalPages = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalResults = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Pictures> getResults() {
        return results;
    }

    public void setResults(List<Pictures> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.page);
        dest.writeList(this.results);
        dest.writeValue(this.totalPages);
        dest.writeValue(this.totalResults);
    }
}
