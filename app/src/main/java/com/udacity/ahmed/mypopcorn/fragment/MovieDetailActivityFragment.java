package com.udacity.ahmed.mypopcorn.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.activity.MovieDetailActivity;
import com.udacity.ahmed.mypopcorn.adapter.MovieListDataAdapter;
import com.udacity.ahmed.mypopcorn.adapter.TvCastAdapter;
import com.udacity.ahmed.mypopcorn.network.RetrofitManager;
import com.udacity.ahmed.mypopcorn.pojo.Cast.Cast;
import com.udacity.ahmed.mypopcorn.pojo.CombinedMovieDetail;
import com.udacity.ahmed.mypopcorn.pojo.Picture.Pictures;
import com.udacity.ahmed.mypopcorn.pojo.Video.VideoResult;
import com.udacity.ahmed.mypopcorn.util.Constants;
import com.udacity.ahmed.mypopcorn.util.DateTimeHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    public static final String TAG = MovieDetailActivityFragment.class.getSimpleName();
    public static final String DETAIL_TV = "DETAIL_TV";

    private int is_fav;
    private int movieId;
    private TextView releaseDate;
    private TextView vote;
    private TextView plotSynopsis;
    private Button fav_button;
    private SimpleDraweeView draweeView;
    private CollapsingToolbarLayout collapsingToolbar;
    private HorizontalGridView videosHzGridView;
    private HorizontalGridView recommendedTvShowsHzGridView;
    private HorizontalGridView similarTvShowsHzGridView;
    private HorizontalGridView tvCastGridView;
    private ProgressBar similarTvShowsProgressBar;
    private ProgressBar recommendedTvShowsProgressBar;
    private ProgressBar videosProgressBar;
    private ProgressBar tvCastProgressBar;
    private TextView tvCastHeading;
    private TextView similarTvShowsHeading;
    private TextView recommendedTvShowsHeading;
    private TextView videosHeading;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private CardView tvSeasonsCardView;
    private CardView similarTvShowsCardView;
    private CardView recommendedTvShowsCardView;
    private View cordinatorLayout;
    private String episodeDate;
    private CombinedMovieDetail combinedMovieDetail;


    public MovieDetailActivityFragment() {
    }


    public static MovieDetailActivityFragment newInstance(CombinedMovieDetail combinedMovieDetail) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("combinedMovieDetail", combinedMovieDetail);
        MovieDetailActivityFragment movieDetailActivityFragment = new MovieDetailActivityFragment();
        movieDetailActivityFragment.setArguments(bundle);
        return movieDetailActivityFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ((MovieDetailActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MovieDetailActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RetrofitManager retrofitManager = RetrofitManager.getInstance();
        collapsingToolbar = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        LinearLayout detailLayout = (LinearLayout) rootView.findViewById(R.id.detail_layout);
        CardView infoCardView = (CardView) rootView.findViewById(R.id.info_card_view);
        TextView title = (TextView) rootView.findViewById(R.id.title);
        releaseDate = (TextView) rootView.findViewById(R.id.release_date);
        vote = (TextView) rootView.findViewById(R.id.vote);
        plotSynopsis = (TextView) rootView.findViewById(R.id.plot_synopsis);
        fav_button = (Button) rootView.findViewById(R.id.b11);
        draweeView = (SimpleDraweeView) getActivity().findViewById(R.id.movie_poster);
        fav_button.setBackground(getContext().getResources().getDrawable(R.drawable.unfav));
        fav_button.setVisibility(View.GONE);
        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progress_fragment);
        cordinatorLayout = getActivity().findViewById(R.id.app_bar);

        prefs = getActivity().getSharedPreferences(
                Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);

        LinearLayout nextEpisodeCardView = (LinearLayout) rootView.findViewById(R.id.nextAirLayout);
        nextEpisodeCardView.setVisibility(View.GONE);

        tvSeasonsCardView = (CardView) rootView.findViewById(R.id.tvSeasonsCard);
        ProgressBar tvSeasonsProgressBar = (ProgressBar) tvSeasonsCardView.findViewById(R.id.progress_main);
        tvSeasonsProgressBar.setVisibility(View.VISIBLE);
        tvSeasonsCardView.setVisibility(View.GONE);

        CardView tvCastCardView = (CardView) rootView.findViewById(R.id.tvCastCard);
        tvCastGridView = (HorizontalGridView) tvCastCardView.findViewById(R.id.horizontal_grid_view);
        tvCastHeading = (TextView) tvCastCardView.findViewById(R.id.heading);
        tvCastProgressBar = (ProgressBar) tvCastCardView.findViewById(R.id.progress_main);
        tvCastProgressBar.setVisibility(View.VISIBLE);

        similarTvShowsCardView = (CardView) rootView.findViewById(R.id.similarTvShowsCard);
        similarTvShowsHzGridView = (HorizontalGridView) similarTvShowsCardView.findViewById(R.id.horizontal_grid_view);
        similarTvShowsHeading = (TextView) similarTvShowsCardView.findViewById(R.id.heading);
        similarTvShowsProgressBar = (ProgressBar) similarTvShowsCardView.findViewById(R.id.progress_main);
        similarTvShowsProgressBar.setVisibility(View.VISIBLE);

        recommendedTvShowsCardView = (CardView) rootView.findViewById(R.id.recommendedTvShowsCard);
        recommendedTvShowsHzGridView = (HorizontalGridView) recommendedTvShowsCardView.findViewById(R.id.horizontal_grid_view);
        recommendedTvShowsHeading = (TextView) recommendedTvShowsCardView.findViewById(R.id.heading);
        recommendedTvShowsProgressBar = (ProgressBar) recommendedTvShowsCardView.findViewById(R.id.progress_main);
        recommendedTvShowsProgressBar.setVisibility(View.VISIBLE);


        Bundle arguments = getArguments();
        combinedMovieDetail = arguments.getParcelable("combinedMovieDetail");

        if (combinedMovieDetail != null) {
            movieId = combinedMovieDetail.getId();
            loadMovieDetails();
            loadSimilarRecommendations(combinedMovieDetail);
        }
        return rootView;
    }


    public void loadMovieDetails() {
        try {
            cordinatorLayout.setVisibility(View.VISIBLE);
            boolean threadAlreadyRunning = false;
            collapsingToolbar.setTitle(combinedMovieDetail.getTitle());
            draweeView.setImageURI(getString(R.string.image_path) + combinedMovieDetail.getBackdropPath());
            releaseDate.setText(getString(R.string.release_data) + DateTimeHelper.parseDate(combinedMovieDetail.getReleaseDate()) + "");
            vote.setText(getString(R.string.rating) + combinedMovieDetail.getVoteAverage() + "/10");
            plotSynopsis.setText(combinedMovieDetail.getOverview());
            is_fav = (prefs.getInt("is_fav" + "_" + movieId, 0));
            if (is_fav == 1) {
                fav_button.setBackground(getContext().getResources().getDrawable(R.drawable.fav));
            } else {
                fav_button.setBackground(getContext().getResources().getDrawable(R.drawable.unfav));
            }

            fav_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (is_fav == 1) {
                        fav_button.setBackground(getContext().getResources().getDrawable(R.drawable.unfav));
                        //                        deleteFromDb();
                        is_fav = 0;
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("is_fav" + "_" + movieId, 0);
                        editor.apply();
                    } else {
                        fav_button.setBackground(getContext().getResources().getDrawable(R.drawable.fav));
                        //                        MyPopcornDBHelper.addintoDB(tvInformation, getContext(), movieId);
                        is_fav = 1;
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("is_fav" + "_" + movieId, 1);
                        editor.apply();
                    }
                }
            });
            List<VideoResult> CombinedMovieDetailVideos = combinedMovieDetail.getVideos().getResults();

//            if (getActivity() != null || (CombinedMovieDetailVideos != null ? CombinedMovieDetailVideos.size() : 0) < 1) {
//                VideoAdapter videoAdapter = new VideoAdapter(getActivity(), CombinedMovieDetailVideos);
//                videosHzGridView.setAdapter(videoAdapter);
//                videoAdapter.notifyDataSetChanged();
//                videosHzGridView.setVisibility(View.VISIBLE);
//                videosProgressBar.setVisibility(View.GONE);
//                videosHeading.setText(R.string.videos_heading);
//                tvSeasonsCardView.setVisibility(View.GONE);
//            } else {
//                tvSeasonsCardView.setVisibility(View.GONE);
//            }
            List<Cast> movieCast = combinedMovieDetail.getCredits().getCast();

            if (getActivity() != null || combinedMovieDetail.getCredits().getCast().size() < 1) {
                TvCastAdapter tvCastAdapter = new TvCastAdapter(getActivity(), movieCast, movieId);
                tvCastGridView.setAdapter(tvCastAdapter);
                tvCastAdapter.notifyDataSetChanged();
                tvCastProgressBar.setVisibility(View.GONE);
                tvCastHeading.setText(R.string.tv_casts_heading);
            } else {
                tvSeasonsCardView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.report(e);

        }
    }


    public void loadSimilarRecommendations(CombinedMovieDetail combinedMovieDetail) {
        List<Pictures> similarMoviesList = combinedMovieDetail.getSimilar().getResults();

        if (similarMoviesList.size() < 1) {
            similarTvShowsCardView.setVisibility(View.GONE);
        } else {
            if (getActivity() != null) {
                MovieListDataAdapter popularTvDataAdapter = new MovieListDataAdapter(getContext(), similarMoviesList, 1);
                similarTvShowsHzGridView.setAdapter(popularTvDataAdapter);
                popularTvDataAdapter.notifyDataSetChanged();
                similarTvShowsHzGridView.setVisibility(View.VISIBLE);
                similarTvShowsProgressBar.setVisibility(View.GONE);
                similarTvShowsHeading.setText(R.string.more_tv_show_heading);
            }
        }
        List<Pictures> recommendedMoviesList = combinedMovieDetail.getRecommendations().getResults();
        if (recommendedMoviesList.size() < 1) {
            recommendedTvShowsCardView.setVisibility(View.GONE);
        } else {
            if (getActivity() != null) {
                MovieListDataAdapter recommendedMovieAdapter = new MovieListDataAdapter(getContext(), recommendedMoviesList, 2);
                recommendedTvShowsHzGridView.setAdapter(recommendedMovieAdapter);
                recommendedMovieAdapter.notifyDataSetChanged();
                recommendedTvShowsHzGridView.setVisibility(View.VISIBLE);
                recommendedTvShowsProgressBar.setVisibility(View.GONE);
                recommendedTvShowsHeading.setText(R.string.you_must_watch_heading);
            }
        }

    }

}
