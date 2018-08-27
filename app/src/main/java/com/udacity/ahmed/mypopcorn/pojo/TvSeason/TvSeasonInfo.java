package com.udacity.ahmed.mypopcorn.pojo.TvSeason;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed Hamdan.
 */

public class TvSeasonInfo {

    @SerializedName("_id")
    @Expose
    private String id1;
    @SerializedName("air_date")
    @Expose
    private String airDate;
    @SerializedName("episodes")
    @Expose
    private List<Episode> episodes = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("season_number")
    @Expose
    private Integer seasonNumber;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }


    public class Crew {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("credit_id")
        @Expose
        private String creditId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("department")
        @Expose
        private String department;
        @SerializedName("job")
        @Expose
        private String job;
        @SerializedName("profile_path")
        @Expose
        private Object profilePath;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCreditId() {
            return creditId;
        }

        public void setCreditId(String creditId) {
            this.creditId = creditId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public Object getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(Object profilePath) {
            this.profilePath = profilePath;
        }

    }


    public class Episode {

        @SerializedName("air_date")
        @Expose
        private String airDate;
        @SerializedName("crew")
        @Expose
        private List<Crew> crew = null;
        @SerializedName("episode_number")
        @Expose
        private Integer episodeNumber;
        @SerializedName("guest_stars")
        @Expose
        private List<GuestStar> guestStars = null;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("overview")
        @Expose
        private String overview;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("production_code")
        @Expose
        private Object productionCode;
        @SerializedName("season_number")
        @Expose
        private Integer seasonNumber;
        @SerializedName("still_path")
        @Expose
        private String stillPath;
        @SerializedName("vote_average")
        @Expose
        private Double voteAverage;
        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;

        public String getAirDate() {
            return airDate;
        }

        public void setAirDate(String airDate) {
            this.airDate = airDate;
        }

        public List<Crew> getCrew() {
            return crew;
        }

        public void setCrew(List<Crew> crew) {
            this.crew = crew;
        }

        public Integer getEpisodeNumber() {
            return episodeNumber;
        }

        public void setEpisodeNumber(Integer episodeNumber) {
            this.episodeNumber = episodeNumber;
        }

        public List<GuestStar> getGuestStars() {
            return guestStars;
        }

        public void setGuestStars(List<GuestStar> guestStars) {
            this.guestStars = guestStars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Object getProductionCode() {
            return productionCode;
        }

        public void setProductionCode(Object productionCode) {
            this.productionCode = productionCode;
        }

        public Integer getSeasonNumber() {
            return seasonNumber;
        }

        public void setSeasonNumber(Integer seasonNumber) {
            this.seasonNumber = seasonNumber;
        }

        public String getStillPath() {
            return stillPath;
        }

        public void setStillPath(String stillPath) {
            this.stillPath = stillPath;
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

    }


    public class GuestStar {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("credit_id")
        @Expose
        private String creditId;
        @SerializedName("character")
        @Expose
        private String character;
        @SerializedName("order")
        @Expose
        private Integer order;
        @SerializedName("profile_path")
        @Expose
        private String profilePath;

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

        public String getCreditId() {
            return creditId;
        }

        public void setCreditId(String creditId) {
            this.creditId = creditId;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
        }

    }
}