package com.udacity.ahmed.mypopcorn.pojo.Cast;

/**
 * Created by Ahmed Hamdan.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastInfo {


    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("also_known_as")
    @Expose
    private List<String> alsoKnownAs = null;
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("deathday")
    @Expose
    private String deathday;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("place_of_birth")
    @Expose
    private String placeOfBirth;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("combined_credits")
    @Expose
    private Credits credits;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }


    public class Credits {

        @SerializedName("cast")
        @Expose
        private List<Cast> cast = null;
        @SerializedName("crew")
        @Expose
        private List<Crew> crew = null;

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

    }

}
