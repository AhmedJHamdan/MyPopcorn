package com.udacity.ahmed.mypopcorn.pojo.Tv;

/**
 * Created by Ahmed Hamdan.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import com.udacity.ahmed.mypopcorn.pojo.Picture.Pictures;

public class Tv {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private ArrayList<Pictures> tvShows = null;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<Pictures> getTvShows() {
        return tvShows;
    }

    public void setTvShows(ArrayList<Pictures> tvShows) {
        this.tvShows = tvShows;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
