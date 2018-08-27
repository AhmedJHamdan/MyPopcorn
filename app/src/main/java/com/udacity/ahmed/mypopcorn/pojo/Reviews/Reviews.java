
package com.udacity.ahmed.mypopcorn.pojo.Reviews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Reviews {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<ReviewsResult> reviewsResults = new ArrayList<>();
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

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
     * @return The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return The reviewsResults
     */
    public List<ReviewsResult> getReviewsResults() {
        return reviewsResults;
    }

    /**
     * @param reviewsResults The reviewsResults
     */
    public void setReviewsResults(List<ReviewsResult> reviewsResults) {
        this.reviewsResults = reviewsResults;
    }

    /**
     * @return The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "com.udacity.ahmed.mypopcorn.pojo.Reviews{" +
                "id=" + id +
                ", page=" + page +
                ", reviewsResults=" + reviewsResults +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
