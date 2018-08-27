package com.udacity.ahmed.mypopcorn.pojo.Cast;

/**
 * Created by Ahmed Hamdan.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class CastDetail {
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
    @SerializedName("crew")
    @Expose
    private List<Crew> crew = null;
    @SerializedName("id")
    @Expose
    private Integer id;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public class Crew {

        @SerializedName("adult")
        @Expose
        private Boolean adult;
        @SerializedName("credit_id")
        @Expose
        private String creditId;
        @SerializedName("department")
        @Expose
        private String department;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("job")
        @Expose
        private String job;
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

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public String getCreditId() {
            return creditId;
        }

        public void setCreditId(String creditId) {
            this.creditId = creditId;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
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

    }

}
