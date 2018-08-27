package com.udacity.ahmed.mypopcorn.pojo.Search;

/**
 * Created by Ahmed Hamdan.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
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

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
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


    public class Result {

        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("popularity")
        @Expose
        private Float popularity;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;
        @SerializedName("vote_average")
        @Expose
        private Float voteAverage;
        @SerializedName("overview")
        @Expose
        private String overview;
        @SerializedName("first_air_date")
        @Expose
        private String firstAirDate;
        @SerializedName("origin_country")
        @Expose
        private List<String> originCountry = null;
        @SerializedName("genre_ids")
        @Expose
        private List<Integer> genreIds = null;
        @SerializedName("original_language")
        @Expose
        private String originalLanguage;
        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("original_name")
        @Expose
        private String originalName;

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public Float getPopularity() {
            return popularity;
        }

        public void setPopularity(Float popularity) {
            this.popularity = popularity;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public Float getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Float voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getFirstAirDate() {
            return firstAirDate;
        }

        public void setFirstAirDate(String firstAirDate) {
            this.firstAirDate = firstAirDate;
        }

        public List<String> getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(List<String> originCountry) {
            this.originCountry = originCountry;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
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

    }
}