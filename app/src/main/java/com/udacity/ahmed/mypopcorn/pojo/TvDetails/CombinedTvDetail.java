package com.udacity.ahmed.mypopcorn.pojo.TvDetails;

/**
 * Created by Ahmed Hamdan.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import com.udacity.ahmed.mypopcorn.pojo.Credits.Credits;
import com.udacity.ahmed.mypopcorn.pojo.Images.Images;
import com.udacity.ahmed.mypopcorn.pojo.Recommendations;
import com.udacity.ahmed.mypopcorn.pojo.Similar;
import com.udacity.ahmed.mypopcorn.pojo.Video.Videos;


public class CombinedTvDetail implements Parcelable {

    public static final Creator<CombinedTvDetail> CREATOR = new Creator<CombinedTvDetail>() {
        @Override
        public CombinedTvDetail createFromParcel(Parcel source) {
            return new CombinedTvDetail(source);
        }

        @Override
        public CombinedTvDetail[] newArray(int size) {
            return new CombinedTvDetail[size];
        }
    };
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("episode_run_time")
    @Expose
    private List<Integer> episodeRunTime = null;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("in_production")
    @Expose
    private Boolean inProduction;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("last_air_date")
    @Expose
    private String lastAirDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("networks")
    @Expose
    private List<Network> networks = null;
    @SerializedName("number_of_episodes")
    @Expose
    private Integer numberOfEpisodes;
    @SerializedName("number_of_seasons")
    @Expose
    private Integer numberOfSeasons;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry = null;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("production_companies")
    @Expose
    private List<Object> productionCompanies = null;
    @SerializedName("seasons")
    @Expose
    private List<Season> seasons = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("credits")
    @Expose
    private Credits credits;
    @SerializedName("videos")
    @Expose
    private Videos videos;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("similar")
    @Expose
    private Similar similar;
    @SerializedName("recommendations")
    @Expose
    private Recommendations recommendations;

    public CombinedTvDetail() {
    }

    protected CombinedTvDetail(Parcel in) {
        this.backdropPath = in.readString();
        this.episodeRunTime = new ArrayList<>();
        in.readList(this.episodeRunTime, Integer.class.getClassLoader());
        this.firstAirDate = in.readString();
        this.genres = new ArrayList<>();
        in.readList(this.genres, Genre.class.getClassLoader());
        this.homepage = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inProduction = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.languages = in.createStringArrayList();
        this.lastAirDate = in.readString();
        this.name = in.readString();
        this.networks = new ArrayList<>();
        in.readList(this.networks, Network.class.getClassLoader());
        this.numberOfEpisodes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.numberOfSeasons = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originCountry = in.createStringArrayList();
        this.originalLanguage = in.readString();
        this.originalName = in.readString();
        this.overview = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.posterPath = in.readString();
        this.productionCompanies = new ArrayList<>();
        in.readList(this.productionCompanies, Object.class.getClassLoader());
        this.seasons = new ArrayList<>();
        in.readList(this.seasons, Season.class.getClassLoader());
        this.status = in.readString();
        this.type = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.credits = in.readParcelable(Credits.class.getClassLoader());
        this.videos = in.readParcelable(Videos.class.getClassLoader());
        this.images = in.readParcelable(Images.class.getClassLoader());
        this.similar = in.readParcelable(Similar.class.getClassLoader());
        this.recommendations = in.readParcelable(Recommendations.class.getClassLoader());
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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

    public Boolean getInProduction() {
        return inProduction;
    }

    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<Object> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<Object> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Similar getSimilar() {
        return similar;
    }

    public void setSimilar(Similar similar) {
        this.similar = similar;
    }

    public Recommendations getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Recommendations recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backdropPath);
        dest.writeList(this.episodeRunTime);
        dest.writeString(this.firstAirDate);
        dest.writeList(this.genres);
        dest.writeString(this.homepage);
        dest.writeValue(this.id);
        dest.writeValue(this.inProduction);
        dest.writeStringList(this.languages);
        dest.writeString(this.lastAirDate);
        dest.writeString(this.name);
        dest.writeList(this.networks);
        dest.writeValue(this.numberOfEpisodes);
        dest.writeValue(this.numberOfSeasons);
        dest.writeStringList(this.originCountry);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalName);
        dest.writeString(this.overview);
        dest.writeValue(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeList(this.productionCompanies);
        dest.writeList(this.seasons);
        dest.writeString(this.status);
        dest.writeString(this.type);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.voteCount);
        dest.writeParcelable(this.credits, flags);
        dest.writeParcelable(this.videos, flags);
        dest.writeParcelable(this.images, flags);
        dest.writeParcelable(this.similar, flags);
        dest.writeParcelable(this.recommendations, flags);
    }


    public class Genre implements Parcelable {

        public final Creator<Genre> CREATOR = new Creator<Genre>() {
            @Override
            public Genre createFromParcel(Parcel source) {
                return new Genre(source);
            }

            @Override
            public Genre[] newArray(int size) {
                return new Genre[size];
            }
        };
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Genre() {
        }

        protected Genre(Parcel in) {
            this.id = (Integer) in.readValue(Integer.class.getClassLoader());
            this.name = in.readString();
        }

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

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeString(this.name);
        }
    }


    public class Network implements Parcelable {

        public final Creator<Network> CREATOR = new Creator<Network>() {
            @Override
            public Network createFromParcel(Parcel source) {
                return new Network(source);
            }

            @Override
            public Network[] newArray(int size) {
                return new Network[size];
            }
        };
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Network() {
        }

        protected Network(Parcel in) {
            this.id = (Integer) in.readValue(Integer.class.getClassLoader());
            this.name = in.readString();
        }

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeString(this.name);
        }
    }


    public class Season implements Parcelable {

        public final Creator<Season> CREATOR = new Creator<Season>() {
            @Override
            public Season createFromParcel(Parcel source) {
                return new Season(source);
            }

            @Override
            public Season[] newArray(int size) {
                return new Season[size];
            }
        };
        @SerializedName("air_date")
        @Expose
        private String airDate;
        @SerializedName("episode_count")
        @Expose
        private Integer episodeCount;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("season_number")
        @Expose
        private Integer seasonNumber;

        public Season() {
        }

        protected Season(Parcel in) {
            this.airDate = in.readString();
            this.episodeCount = (Integer) in.readValue(Integer.class.getClassLoader());
            this.id = (Integer) in.readValue(Integer.class.getClassLoader());
            this.posterPath = in.readString();
            this.seasonNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        }

        public String getAirDate() {
            return airDate;
        }

        public void setAirDate(String airDate) {
            this.airDate = airDate;
        }

        public Integer getEpisodeCount() {
            return episodeCount;
        }

        public void setEpisodeCount(Integer episodeCount) {
            this.episodeCount = episodeCount;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.airDate);
            dest.writeValue(this.episodeCount);
            dest.writeValue(this.id);
            dest.writeString(this.posterPath);
            dest.writeValue(this.seasonNumber);
        }
    }

}