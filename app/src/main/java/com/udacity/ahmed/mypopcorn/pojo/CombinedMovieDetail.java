package com.udacity.ahmed.mypopcorn.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import com.udacity.ahmed.mypopcorn.pojo.Credits.Credits;
import com.udacity.ahmed.mypopcorn.pojo.Images.Images;
import com.udacity.ahmed.mypopcorn.pojo.Video.Videos;

public class CombinedMovieDetail implements Parcelable {

    public static final Creator<CombinedMovieDetail> CREATOR = new Creator<CombinedMovieDetail>() {
        @Override
        public CombinedMovieDetail createFromParcel(Parcel source) {
            return new CombinedMovieDetail(source);
        }

        @Override
        public CombinedMovieDetail[] newArray(int size) {
            return new CombinedMovieDetail[size];
        }
    };
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    @Expose
    private BelongsToCollection belongsToCollection;
    @SerializedName("budget")
    @Expose
    private Integer budget;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
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
    private List<ProductionCompany> productionCompanies = null;
    @SerializedName("production_countries")
    @Expose
    private List<ProductionCountry> productionCountries = null;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("revenue")
    @Expose
    private Integer revenue;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguage> spokenLanguages = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("videos")
    @Expose
    private Videos videos;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("credits")
    @Expose
    private Credits credits;
    @SerializedName("reviews")
    @Expose
    private Reviews reviews;
    @SerializedName("similar")
    @Expose
    private Similar similar;
    @SerializedName("recommendations")
    @Expose
    private Recommendations recommendations;

    public CombinedMovieDetail() {
    }

    protected CombinedMovieDetail(Parcel in) {
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.backdropPath = in.readString();
        this.belongsToCollection = in.readParcelable(BelongsToCollection.class.getClassLoader());
        this.budget = (Integer) in.readValue(Integer.class.getClassLoader());
        this.genres = new ArrayList<>();
        in.readList(this.genres, Genre.class.getClassLoader());
        this.homepage = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imdbId = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.overview = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.posterPath = in.readString();
        this.productionCompanies = new ArrayList<>();
        in.readList(this.productionCompanies, ProductionCompany.class.getClassLoader());
        this.productionCountries = new ArrayList<>();
        in.readList(this.productionCountries, ProductionCountry.class.getClassLoader());
        this.releaseDate = in.readString();
        this.revenue = (Integer) in.readValue(Integer.class.getClassLoader());
        this.runtime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.spokenLanguages = new ArrayList<>();
        in.readList(this.spokenLanguages, SpokenLanguage.class.getClassLoader());
        this.status = in.readString();
        this.tagline = in.readString();
        this.title = in.readString();
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.videos = in.readParcelable(Videos.class.getClassLoader());
        this.images = in.readParcelable(Images.class.getClassLoader());
        this.credits = in.readParcelable(Credits.class.getClassLoader());
        this.reviews = in.readParcelable(Reviews.class.getClassLoader());
        this.similar = in.readParcelable(Similar.class.getClassLoader());
        this.recommendations = in.readParcelable(Recommendations.class.getClassLoader());
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
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

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
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

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
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
        dest.writeValue(this.adult);
        dest.writeString(this.backdropPath);
        dest.writeParcelable(this.belongsToCollection, flags);
        dest.writeValue(this.budget);
        dest.writeList(this.genres);
        dest.writeString(this.homepage);
        dest.writeValue(this.id);
        dest.writeString(this.imdbId);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeString(this.overview);
        dest.writeValue(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeList(this.productionCompanies);
        dest.writeList(this.productionCountries);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.revenue);
        dest.writeValue(this.runtime);
        dest.writeList(this.spokenLanguages);
        dest.writeString(this.status);
        dest.writeString(this.tagline);
        dest.writeString(this.title);
        dest.writeValue(this.video);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.voteCount);
        dest.writeParcelable(this.videos, flags);
        dest.writeParcelable(this.images, flags);
        dest.writeParcelable(this.credits, flags);
        dest.writeParcelable(this.reviews, flags);
        dest.writeParcelable(this.similar, flags);
        dest.writeParcelable(this.recommendations, flags);
    }


    public class BelongsToCollection implements Parcelable {

        public final Creator<BelongsToCollection> CREATOR = new Creator<BelongsToCollection>() {
            @Override
            public BelongsToCollection createFromParcel(Parcel source) {
                return new BelongsToCollection(source);
            }

            @Override
            public BelongsToCollection[] newArray(int size) {
                return new BelongsToCollection[size];
            }
        };
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;

        public BelongsToCollection() {
        }

        protected BelongsToCollection(Parcel in) {
            this.id = (Integer) in.readValue(Integer.class.getClassLoader());
            this.name = in.readString();
            this.posterPath = in.readString();
            this.backdropPath = in.readString();
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

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeString(this.name);
            dest.writeString(this.posterPath);
            dest.writeString(this.backdropPath);
        }
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
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeString(this.name);
        }
    }


    public class ProductionCompany implements Parcelable {

        public final Creator<ProductionCompany> CREATOR = new Creator<ProductionCompany>() {
            @Override
            public ProductionCompany createFromParcel(Parcel source) {
                return new ProductionCompany(source);
            }

            @Override
            public ProductionCompany[] newArray(int size) {
                return new ProductionCompany[size];
            }
        };
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("id")
        @Expose
        private Integer id;

        public ProductionCompany() {
        }

        protected ProductionCompany(Parcel in) {
            this.name = in.readString();
            this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeValue(this.id);
        }
    }

    public class ProductionCountry implements Parcelable {

        public final Creator<ProductionCountry> CREATOR = new Creator<ProductionCountry>() {
            @Override
            public ProductionCountry createFromParcel(Parcel source) {
                return new ProductionCountry(source);
            }

            @Override
            public ProductionCountry[] newArray(int size) {
                return new ProductionCountry[size];
            }
        };
        @SerializedName("iso_3166_1")
        @Expose
        private String iso31661;
        @SerializedName("name")
        @Expose
        private String name;

        public ProductionCountry() {
        }

        protected ProductionCountry(Parcel in) {
            this.iso31661 = in.readString();
            this.name = in.readString();
        }

        public String getIso31661() {
            return iso31661;
        }

        public void setIso31661(String iso31661) {
            this.iso31661 = iso31661;
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
            dest.writeString(this.iso31661);
            dest.writeString(this.name);
        }
    }


    public class Reviews implements Parcelable {

        public final Creator<Reviews> CREATOR = new Creator<Reviews>() {
            @Override
            public Reviews createFromParcel(Parcel source) {
                return new Reviews(source);
            }

            @Override
            public Reviews[] newArray(int size) {
                return new Reviews[size];
            }
        };
        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("results")
        @Expose
        private List<Object> results = null;
        @SerializedName("total_pages")
        @Expose
        private Integer totalPages;
        @SerializedName("total_results")
        @Expose
        private Integer totalResults;

        public Reviews() {
        }

        protected Reviews(Parcel in) {
            this.page = (Integer) in.readValue(Integer.class.getClassLoader());
            this.results = new ArrayList<>();
            in.readList(this.results, Object.class.getClassLoader());
            this.totalPages = (Integer) in.readValue(Integer.class.getClassLoader());
            this.totalResults = (Integer) in.readValue(Integer.class.getClassLoader());
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public List<Object> getResults() {
            return results;
        }

        public void setResults(List<Object> results) {
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

    public class SpokenLanguage implements Parcelable {

        public final Creator<SpokenLanguage> CREATOR = new Creator<SpokenLanguage>() {
            @Override
            public SpokenLanguage createFromParcel(Parcel source) {
                return new SpokenLanguage(source);
            }

            @Override
            public SpokenLanguage[] newArray(int size) {
                return new SpokenLanguage[size];
            }
        };
        @SerializedName("iso_639_1")
        @Expose
        private String iso6391;
        @SerializedName("name")
        @Expose
        private String name;

        public SpokenLanguage() {
        }

        protected SpokenLanguage(Parcel in) {
            this.iso6391 = in.readString();
            this.name = in.readString();
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
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
            dest.writeString(this.iso6391);
            dest.writeString(this.name);
        }
    }
}