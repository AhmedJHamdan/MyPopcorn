package com.udacity.ahmed.mypopcorn.network;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import com.udacity.ahmed.mypopcorn.pojo.Cast.CastInfo;
import com.udacity.ahmed.mypopcorn.pojo.CombinedMovieDetail;
import com.udacity.ahmed.mypopcorn.pojo.Episode.EpisodeInfo;
import com.udacity.ahmed.mypopcorn.pojo.Picture.Picture_Detail;
import com.udacity.ahmed.mypopcorn.pojo.Reviews.Reviews;
import com.udacity.ahmed.mypopcorn.pojo.Search.SearchResult;
import com.udacity.ahmed.mypopcorn.pojo.Tv.Tv;
import com.udacity.ahmed.mypopcorn.pojo.TvDetails.CombinedTvDetail;
import com.udacity.ahmed.mypopcorn.pojo.TvSeason.TvSeasonInfo;
import com.udacity.ahmed.mypopcorn.pojo.Video.Videos;

/**
 * Created by Ahmed Hamdan.
 */

interface DataInterface {

    @GET("3/movie/{categories}")
    Observable<Picture_Detail> listMoviesInfo(@Path("categories") String categories, @Query("page") int page, @Query("api_key") String apiKey);

    @GET("3/tv/{id}/videos")
    Observable<Videos> listVideos(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/reviews")
    Observable<Reviews> listReviews(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/tv/{id}")
    Observable<Tv> listTvShows(@Path("id") String id, @Query("api_key") String apiKey, @Query("page") int page);


    @GET("3/tv/{id}")
    Observable<CombinedTvDetail> getTvInfo(@Path("id") String id, @Query("api_key") String apiKey, @Query("append_to_response") String credits);


    @GET("3/movie/{id}")
    Observable<CombinedMovieDetail> getMovieDetail(@Path("id") int id, @Query("api_key") String apiKey, @Query("append_to_response") String credits);


    @GET("3/tv/{id}/season/{season}")
    Observable<TvSeasonInfo> getTvSeasonInfo(@Path("id") String id, @Path("season") String season, @Query("api_key") String apiKey);

    @GET("3/tv/{id}/similar")
    Observable<Tv> getSimilarTvShows(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("3/tv/{id}/recommendations")
    Observable<Tv> getRecommendedTvShows(@Path("id") String id, @Query("api_key") String apiKey);


    @GET("3/movie/{id}/similar")
    Observable<Picture_Detail> getSimilarMovies(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/recommendations")
    Observable<Picture_Detail> getRecommendedMovies(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/tv/{id}/season/{season}/episode/{episodeId}")
    Observable<EpisodeInfo> getEpisodeInfo(@Path("id") String id, @Path("season") String episodeId, @Path("episodeId") String season, @Query("api_key") String apiKey);

    @GET("3/search/tv")
    Observable<SearchResult> searchTvShows(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("3/person/{id}")
    Observable<CastInfo> getCastInfo(@Path("id") String id, @Query("api_key") String apiKey, @Query("language") String language, @Query("append_to_response") String credits);
}
