package com.udacity.ahmed.mypopcorn.pojo.TvDetails;//package com.udacity.ahmed.mypopcorn.pojo.TvDetails;
//
//import android.support.annotation.NonNull;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import java.util.List;
//
//import com.udacity.ahmed.mypopcorn.pojo.Cast.Cast;
//
///**
// * Created by Ahmed Hamdan.
// */
//
//public class TvInfo {
//
//    @SerializedName("backdrop_path")
//    @Expose
//    private String backdropPath;
//    @SerializedName("created_by")
//    @Expose
//    private List<CreatedBy> createdBy = null;
//    @SerializedName("episode_run_time")
//    @Expose
//    private List<Integer> episodeRunTime = null;
//    @SerializedName("first_air_date")
//    @Expose
//    private String firstAirDate;
//    @SerializedName("genres")
//    @Expose
//    private List<com.udacity.ahmed.mypopcorn.pojo.Genre> genres = null;
//    @SerializedName("homepage")
//    @Expose
//    private String homepage;
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("in_production")
//    @Expose
//    private Boolean inProduction;
//    @SerializedName("languages")
//    @Expose
//    private List<String> languages = null;
//    @SerializedName("last_air_date")
//    @Expose
//    private String lastAirDate;
//    @SerializedName("name")
//    @Expose
//    private String name;
//    @SerializedName("networks")
//    @Expose
//    private List<Network> networks = null;
//    @SerializedName("number_of_episodes")
//    @Expose
//    private Integer numberOfEpisodes;
//    @SerializedName("number_of_seasons")
//    @Expose
//    private Integer numberOfSeasons;
//    @SerializedName("origin_country")
//    @Expose
//    private List<String> originCountry = null;
//    @SerializedName("original_language")
//    @Expose
//    private String originalLanguage;
//    @SerializedName("original_name")
//    @Expose
//    private String originalName;
//    @SerializedName("overview")
//    @Expose
//    private String overview;
//    @SerializedName("popularity")
//    @Expose
//    private Double popularity;
//    @SerializedName("poster_path")
//    @Expose
//    private String posterPath;
//    @SerializedName("production_companies")
//    @Expose
//    private List<com.udacity.ahmed.mypopcorn.pojo.ProductionCompany> productionCompanies = null;
//    @SerializedName("seasons")
//    @Expose
//    private List<Season> seasons = null;
//    @SerializedName("status")
//    @Expose
//    private String status;
//    @SerializedName("type")
//    @Expose
//    private String type;
//    @SerializedName("vote_average")
//    @Expose
//    private Double voteAverage;
//    @SerializedName("vote_count")
//    @Expose
//    private Integer voteCount;
//    @SerializedName("credits")
//    @Expose
//    private com.udacity.ahmed.mypopcorn.pojo.Credits credits;
//
//    public String getBackdropPath() {
//        return backdropPath;
//    }
//
//    public void setBackdropPath(String backdropPath) {
//        this.backdropPath = backdropPath;
//    }
//
//    public List<CreatedBy> getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(List<CreatedBy> createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public List<Integer> getEpisodeRunTime() {
//        return episodeRunTime;
//    }
//
//    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
//        this.episodeRunTime = episodeRunTime;
//    }
//
//    public String getFirstAirDate() {
//        return firstAirDate;
//    }
//
//    public void setFirstAirDate(String firstAirDate) {
//        this.firstAirDate = firstAirDate;
//    }
//
//    public List<com.udacity.ahmed.mypopcorn.pojo.Genre> getGenres() {
//        return genres;
//    }
//
//    public void setGenres(List<com.udacity.ahmed.mypopcorn.pojo.Genre> genres) {
//        this.genres = genres;
//    }
//
//    public String getHomepage() {
//        return homepage;
//    }
//
//    public void setHomepage(String homepage) {
//        this.homepage = homepage;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Boolean getInProduction() {
//        return inProduction;
//    }
//
//    public void setInProduction(Boolean inProduction) {
//        this.inProduction = inProduction;
//    }
//
//    public List<String> getLanguages() {
//        return languages;
//    }
//
//    public void setLanguages(List<String> languages) {
//        this.languages = languages;
//    }
//
//    public String getLastAirDate() {
//        return lastAirDate;
//    }
//
//    public void setLastAirDate(String lastAirDate) {
//        this.lastAirDate = lastAirDate;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Network> getNetworks() {
//        return networks;
//    }
//
//    public void setNetworks(List<Network> networks) {
//        this.networks = networks;
//    }
//
//    public Integer getNumberOfEpisodes() {
//        return numberOfEpisodes;
//    }
//
//    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
//        this.numberOfEpisodes = numberOfEpisodes;
//    }
//
//    public Integer getNumberOfSeasons() {
//        return numberOfSeasons;
//    }
//
//    public void setNumberOfSeasons(Integer numberOfSeasons) {
//        this.numberOfSeasons = numberOfSeasons;
//    }
//
//    public List<String> getOriginCountry() {
//        return originCountry;
//    }
//
//    public void setOriginCountry(List<String> originCountry) {
//        this.originCountry = originCountry;
//    }
//
//    public String getOriginalLanguage() {
//        return originalLanguage;
//    }
//
//    public void setOriginalLanguage(String originalLanguage) {
//        this.originalLanguage = originalLanguage;
//    }
//
//    public String getOriginalName() {
//        return originalName;
//    }
//
//    public void setOriginalName(String originalName) {
//        this.originalName = originalName;
//    }
//
//    public String getOverview() {
//        return overview;
//    }
//
//    public void setOverview(String overview) {
//        this.overview = overview;
//    }
//
//    public Double getPopularity() {
//        return popularity;
//    }
//
//    public void setPopularity(Double popularity) {
//        this.popularity = popularity;
//    }
//
//    public String getPosterPath() {
//        return posterPath;
//    }
//
//    public void setPosterPath(String posterPath) {
//        this.posterPath = posterPath;
//    }
//
//    public List<com.udacity.ahmed.mypopcorn.pojo.ProductionCompany> getProductionCompanies() {
//        return productionCompanies;
//    }
//
//    public void setProductionCompanies(List<com.udacity.ahmed.mypopcorn.pojo.ProductionCompany> productionCompanies) {
//        this.productionCompanies = productionCompanies;
//    }
//
//    public List<Season> getSeasons() {
//        return seasons;
//    }
//
//    public void setSeasons(List<Season> seasons) {
//        this.seasons = seasons;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Double getVoteAverage() {
//        return voteAverage;
//    }
//
//    public void setVoteAverage(Double voteAverage) {
//        this.voteAverage = voteAverage;
//    }
//
//    public Integer getVoteCount() {
//        return voteCount;
//    }
//
//    public void setVoteCount(Integer voteCount) {
//        this.voteCount = voteCount;
//    }
//
//    public com.udacity.ahmed.mypopcorn.pojo.Credits getCredits() {
//        return credits;
//    }
//
//    public void setCredits(com.udacity.ahmed.mypopcorn.pojo.Credits credits) {
//        this.credits = credits;
//    }
//
//
//
//    public class CreatedBy {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("name")
//        @Expose
//        private String name;
//        @SerializedName("profile_path")
//        @Expose
//        private Object profilePath;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Object getProfilePath() {
//            return profilePath;
//        }
//
//        public void setProfilePath(Object profilePath) {
//            this.profilePath = profilePath;
//        }
//
//    }
//
//    public class com.udacity.ahmed.mypopcorn.pojo.Credits {
//
//        @SerializedName("cast")
//        @Expose
//        private List<Cast> cast = null;
//        @SerializedName("crew")
//        @Expose
//        private List<Crew> crew = null;
//
//        public List<Cast> getCast() {
//            return cast;
//        }
//
//        public void setCast(List<Cast> cast) {
//            this.cast = cast;
//        }
//
//        public List<Crew> getCrew() {
//            return crew;
//        }
//
//        public void setCrew(List<Crew> crew) {
//            this.crew = crew;
//        }
//
//    }
//
//    public class Crew {
//
//        @SerializedName("credit_id")
//        @Expose
//        private String creditId;
//        @SerializedName("department")
//        @Expose
//        private String department;
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("name")
//        @Expose
//        private String name;
//        @SerializedName("gender")
//        @Expose
//        private Integer gender;
//        @SerializedName("job")
//        @Expose
//        private String job;
//        @SerializedName("profile_path")
//        @Expose
//        private Object profilePath;
//
//        public String getCreditId() {
//            return creditId;
//        }
//
//        public void setCreditId(String creditId) {
//            this.creditId = creditId;
//        }
//
//        public String getDepartment() {
//            return department;
//        }
//
//        public void setDepartment(String department) {
//            this.department = department;
//        }
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Integer getGender() {
//            return gender;
//        }
//
//        public void setGender(Integer gender) {
//            this.gender = gender;
//        }
//
//        public String getJob() {
//            return job;
//        }
//
//        public void setJob(String job) {
//            this.job = job;
//        }
//
//        public Object getProfilePath() {
//            return profilePath;
//        }
//
//        public void setProfilePath(Object profilePath) {
//            this.profilePath = profilePath;
//        }
//
//    }
//
//
//    public class com.udacity.ahmed.mypopcorn.pojo.Genre {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("name")
//        @Expose
//        private String name;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//    }
//
//
//    public class Network {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("name")
//        @Expose
//        private String name;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//    }
//
//
//    public class com.udacity.ahmed.mypopcorn.pojo.ProductionCompany {
//
//        @SerializedName("name")
//        @Expose
//        private String name;
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//    }
//
//
//    public class Season implements Comparable<Season> {
//
//        @SerializedName("air_date")
//        @Expose
//        private String airDate;
//        @SerializedName("episode_count")
//        @Expose
//        private Integer episodeCount;
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("poster_path")
//        @Expose
//        private String posterPath;
//        @SerializedName("season_number")
//        @Expose
//        private Integer seasonNumber;
//
//        public String getAirDate() {
//            return airDate;
//        }
//
//        public void setAirDate(String airDate) {
//            this.airDate = airDate;
//        }
//
//        public Integer getEpisodeCount() {
//            return episodeCount;
//        }
//
//        public void setEpisodeCount(Integer episodeCount) {
//            this.episodeCount = episodeCount;
//        }
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getPosterPath() {
//            return posterPath;
//        }
//
//        public void setPosterPath(String posterPath) {
//            this.posterPath = posterPath;
//        }
//
//        public Integer getSeasonNumber() {
//            return seasonNumber;
//        }
//
//        public void setSeasonNumber(Integer seasonNumber) {
//            this.seasonNumber = seasonNumber;
//        }
//
//        @Override
//        public int compareTo(@NonNull Season o) {
//            return this.getId() - o.getId();
//        }
//    }
//}