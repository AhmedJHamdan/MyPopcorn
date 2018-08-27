package com.udacity.ahmed.mypopcorn.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.activity.MovieDetailActivity;
import com.udacity.ahmed.mypopcorn.activity.ViewAllActivity;
import com.udacity.ahmed.mypopcorn.adapter.MovieListDataAdapter;
import com.udacity.ahmed.mypopcorn.data.MyPopcornContract;
import com.udacity.ahmed.mypopcorn.data.MyPopcornDBHelper;
import com.udacity.ahmed.mypopcorn.eventbus.MessageEvent;
import com.udacity.ahmed.mypopcorn.network.RetrofitManager;
import com.udacity.ahmed.mypopcorn.pojo.Picture.Picture_Detail;
import com.udacity.ahmed.mypopcorn.pojo.Picture.Pictures;
import com.udacity.ahmed.mypopcorn.util.Constants;
import com.udacity.ahmed.mypopcorn.util.DateTimeHelper;

public class MovieFragment extends Fragment {

    private FirebaseAnalytics mFirebaseAnalytics;
    private EventBus eventBus;
    private SharedPreferences.Editor editor;
    private boolean threadAlreadyRunning = false;
    private RetrofitManager retrofitManager;
    private List<Pictures> topRatedMoviesList = new ArrayList<>();
    private List<Pictures> popularMoviesList = new ArrayList<>();
    private List<Pictures> upcomingMoviesList = new ArrayList<>();
    private List<Pictures> nowPlayingMoviesList = new ArrayList<>();
    private HorizontalGridView popularTvShowsHzGridView, topRatedTvshowsHzGridView, todayAirTvShowsHzGridView;
    private ProgressBar popularTvShowsProgressBar, topRatedTvShowsProgressBar, todayAirTvShowsProgressBar;
    private SharedPreferences prefs;
    private TextView popularTvShowHeading;
    private TextView topRatedTvshowHeading;
    private TextView todayAirTvTvShowHeading;
    private LinearLayout layout;
    private Context mContext;
    private List<Pictures> dataList = new ArrayList<>();
    private CarouselView carousel_view;
    private AdView nativeView;

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        MobileAds.initialize(getContext(), Constants.ADUNIT);
        mContext = getActivity();
        new MyPopcornDBHelper(mContext);
        eventBus = EventBus.getDefault();

        carousel_view = (CarouselView) view.findViewById(R.id.carouselView);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(mContext);

        nativeView = (AdView) view.findViewById(R.id.adView);
        handleAdView();

        prefs = mContext.getSharedPreferences(
                "com.udacity.ahmed.mypopcorn", Context.MODE_PRIVATE);

        retrofitManager = RetrofitManager.getInstance();
        layout = (LinearLayout) view.findViewById(R.id.layout_main);

        popularTvShowsHzGridView = (HorizontalGridView) view.findViewById(R.id.popularTvShowsCard).findViewById(R.id.horizontal_grid_view);
        popularTvShowsProgressBar = (ProgressBar) view.findViewById(R.id.popularTvShowsCard).findViewById(R.id.progress_main);
        popularTvShowsProgressBar.setVisibility(View.VISIBLE);
        popularTvShowHeading = (TextView) view.findViewById(R.id.popularTvShowsCard).findViewById(R.id.heading);
        TextView popular_view_all_tx = (TextView) view.findViewById(R.id.popularTvShowsCard).findViewById(R.id.view_all_tx);
        popular_view_all_tx.setVisibility(View.VISIBLE);
        popular_view_all_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionOnclick("popular");
            }
        });

        topRatedTvshowsHzGridView = (HorizontalGridView) view.findViewById(R.id.topRatedTvShowsCard).findViewById(R.id.horizontal_grid_view);
        topRatedTvShowsProgressBar = (ProgressBar) view.findViewById(R.id.topRatedTvShowsCard).findViewById(R.id.progress_main);
        topRatedTvShowsProgressBar.setVisibility(View.VISIBLE);
        topRatedTvshowHeading = (TextView) view.findViewById(R.id.topRatedTvShowsCard).findViewById(R.id.heading);
        TextView topRated_view_all_tx = (TextView) view.findViewById(R.id.topRatedTvShowsCard).findViewById(R.id.view_all_tx);
        topRated_view_all_tx.setVisibility(View.VISIBLE);
        topRated_view_all_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionOnclick("top_rated");
            }
        });

        todayAirTvShowsHzGridView = (HorizontalGridView) view.findViewById(R.id.airTodayTvShowsCard).findViewById(R.id.horizontal_grid_view);
        todayAirTvShowsProgressBar = (ProgressBar) view.findViewById(R.id.airTodayTvShowsCard).findViewById(R.id.progress_main);
        todayAirTvShowsProgressBar.setVisibility(View.VISIBLE);
        todayAirTvTvShowHeading = (TextView) view.findViewById(R.id.airTodayTvShowsCard).findViewById(R.id.heading);
        TextView todayAir_view_all_tx = (TextView) view.findViewById(R.id.airTodayTvShowsCard).findViewById(R.id.view_all_tx);
        todayAir_view_all_tx.setVisibility(View.VISIBLE);
        todayAir_view_all_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionOnclick("upcoming");
            }
        });

        popularTvShowsHzGridView.setVisibility(View.INVISIBLE);
        topRatedTvshowsHzGridView.setVisibility(View.INVISIBLE);
        todayAirTvShowsHzGridView.setVisibility(View.INVISIBLE);
        new MainPageThread(1).start();
        readFromDatabase();
        return view;
    }

    private void handleAdView() {

        nativeView.loadAd(new AdRequest.Builder().addTestDevice("D938443E0DE7112D76DF6BBA67607EB5").build());

        nativeView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                nativeView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                nativeView.setVisibility(View.GONE);
            }

        });
    }


    @Override
    public void onResume() {
        super.onResume();
//        eventBus = EventBus.getDefault();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }


    private void readFromDatabase() {
        MyPopcornDBHelper dbHelper = new MyPopcornDBHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + MyPopcornContract.StayTunedEntry.TABLE_NAME, null);
        Log.v("count ", cursor.getCount() + "");
        cursor.close();
    }

    private void fetchListTypeDataFromServer(String listType, int eventType) {
        Random rand = new Random();
        int pageToQuery = rand.nextInt(5) + 1;

        switch (eventType) {
            case 2:
                listType = "top_rated";
                break;


        }

        Observable<Picture_Detail> topRatedObservable = retrofitManager.listMoviesInfo(listType, pageToQuery);

        topRatedObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Picture_Detail>() {
                               @Override
                               public void onCompleted() {
                                   if (dataList != null) {
                                       eventBus.post(new MessageEvent(2));
                                   }
                                   String topRatedTvJSONList = new Gson().toJson(topRatedMoviesList);
                                   SharedPreferences.Editor editor = prefs.edit();
                                   editor.putString("topRatedMoviesList", topRatedTvJSONList);
                                   editor.apply();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   FirebaseCrash.report(e);

                                   Log.v("Exception", "NullPointerException");
                               }

                               @Override
                               public void onNext(Picture_Detail tv) {
                                   dataList = tv.getResults();
                               }
                           }
                );


    }

    private void fetchTopRatedDataFromServer() {
        Random rand = new Random();
        int pageToQuery = rand.nextInt(5) + 1;
        Observable<Picture_Detail> topRatedObservable = retrofitManager.listMoviesInfo("top_rated", pageToQuery);

        topRatedObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Picture_Detail>() {
                               @Override
                               public void onCompleted() {
                                   if (topRatedMoviesList != null)
                                       eventBus.post(new MessageEvent(2));

                                   String topRatedTvJSONList = new Gson().toJson(topRatedMoviesList);
                                   SharedPreferences.Editor editor = prefs.edit();
                                   editor.putString("topRatedMoviesList", topRatedTvJSONList);
                                   editor.apply();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   FirebaseCrash.report(e);
                                   topRatedTvshowsHzGridView.setVisibility(View.GONE);
                                   Log.v("Exception", "NullPointerException");
                               }

                               @Override
                               public void onNext(Picture_Detail tv) {
                                   topRatedMoviesList = tv.getResults();
                               }
                           }
                );


    }


    private void fetchPopularDataFromServer() {
        Random rand = new Random();
        int pageToQuery = rand.nextInt(5) + 1;
        Observable<Picture_Detail> popularObservable = retrofitManager.listMoviesInfo("popular", pageToQuery);

        popularObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Picture_Detail>() {
                               @Override
                               public void onCompleted() {
                                   if (popularMoviesList != null)
                                       eventBus.post(new MessageEvent(1));
                                   String topRatedTvJSONList = new Gson().toJson(popularMoviesList);
                                   SharedPreferences.Editor editor = prefs.edit();
                                   editor.putString("popularMoviesList", topRatedTvJSONList);
                                   editor.apply();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   FirebaseCrash.report(e);
                                   popularTvShowsHzGridView.setVisibility(View.GONE);
                                   Log.v("Exception", "NullPointerEx/ception");
                               }


                               @Override
                               public void onNext(Picture_Detail tv) {
                                   popularMoviesList = tv.getResults();
                               }
                           }

                );
    }

    private void fetchLatestDataFromServer() {
        Observable<Picture_Detail> airingTodayObservable = retrofitManager.listMoviesInfo("upcoming", 1);

        airingTodayObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Picture_Detail>() {
                               @Override
                               public void onCompleted() {
                                   if (upcomingMoviesList != null)
                                       eventBus.post(new MessageEvent(3));
                                   String topRatedTvJSONList = new Gson().toJson(upcomingMoviesList);
                                   SharedPreferences.Editor editor = prefs.edit();
                                   editor.putString("upcomingMoviesList", topRatedTvJSONList);
                                   editor.apply();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   FirebaseCrash.report(e);
                                   todayAirTvShowsHzGridView.setVisibility(View.GONE);
                                   Log.v("Exception", "NullPointerException");
                               }

                               @Override
                               public void onNext(Picture_Detail tv) {
                                   upcomingMoviesList = tv.getResults();
                               }
                           }

                );

    }

    private void showSnakeBar(View view) {
        try {
            Snackbar snackbar = Snackbar
                    .make(view, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction(null, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                            context.startActivity(intent);

                        }
                    });
            snackbar.setActionTextColor(Color.WHITE);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.cardview_dark_background, null));

            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        } catch (Exception e) {
            Log.v("exception", e + "");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        threadAlreadyRunning = false;
        if (event.getRequest() == 1) {
            MovieListDataAdapter popularTvDataAdapter = new MovieListDataAdapter(mContext, popularMoviesList, 1);
            popularTvShowsHzGridView.setAdapter(popularTvDataAdapter);
            popularTvDataAdapter.notifyDataSetChanged();
            popularTvShowsHzGridView.setVisibility(View.VISIBLE);
            popularTvShowsProgressBar.setVisibility(View.GONE);
            popularTvShowHeading.setText(R.string.popular_movies);
        } else if (event.getRequest() == 2) {
            MovieListDataAdapter topRatedTvDataAdapter = new MovieListDataAdapter(mContext, topRatedMoviesList, 2);
            topRatedTvshowsHzGridView.setAdapter(topRatedTvDataAdapter);
            topRatedTvDataAdapter.notifyDataSetChanged();
            topRatedTvshowsHzGridView.setVisibility(View.VISIBLE);
            topRatedTvShowsProgressBar.setVisibility(View.GONE);
            topRatedTvshowHeading.setText(R.string.top_rated_movies);
        } else if (event.getRequest() == 3) {
            MovieListDataAdapter todayAirTvDataAdapter = new MovieListDataAdapter(mContext, upcomingMoviesList, 1);
            todayAirTvShowsHzGridView.setAdapter(todayAirTvDataAdapter);
            todayAirTvDataAdapter.notifyDataSetChanged();
            todayAirTvShowsHzGridView.setVisibility(View.VISIBLE);
            todayAirTvShowsProgressBar.setVisibility(View.GONE);
            todayAirTvTvShowHeading.setText(R.string.upcoming_movies);

        } else if (event.getRequest() == 4) {
            showSnakeBar(layout);
        }
    }

    private void actionOnclick(String type) {
        Intent intent = new Intent(mContext, ViewAllActivity.class);
        intent.putExtra("listType", type);
        intent.putExtra("categoryType", 1);
        startActivity(intent);
    }

    private void fetchNowPlayingDataFromServer() {
        Observable<Picture_Detail> airingTodayObservable = retrofitManager.listMoviesInfo("now_playing", 1);

        airingTodayObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Picture_Detail>() {
                               @Override
                               public void onCompleted() {
                                   if (nowPlayingMoviesList != null)
                                       eventBus.post(new MessageEvent(3));
                                   String topRatedTvJSONList = new Gson().toJson(nowPlayingMoviesList);
                                   SharedPreferences.Editor editor = prefs.edit();
                                   editor.putString("upcomingMoviesList", topRatedTvJSONList);
                                   editor.apply();

                                   // set ViewListener for custom view
                                   carousel_view.setViewListener(new ViewListener() {
                                       @Override
                                       public View setViewForPosition(final int position) {
                                           View itemView = getActivity().getLayoutInflater().inflate(R.layout.caraousel_movie_layout, null);
                                           TextView tvMovieTitle;
                                           SimpleDraweeView draweeView;
                                           CardView cardView;

                                           tvMovieTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
                                           draweeView = (SimpleDraweeView) itemView.findViewById(R.id.img_movie_poster);

                                           cardView = (CardView) itemView.findViewById(R.id.card_view);

                                           cardView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Intent intent = new Intent(mContext, MovieDetailActivity.class)
                                                           .putExtra(MovieDetailActivityFragment.DETAIL_TV, nowPlayingMoviesList.get(position).getId())
                                                           .putExtra("ListToOpen", 1);
                                                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                   Bundle bundle = new Bundle();
                                                   bundle.putString(FirebaseAnalytics.Param.ITEM_ID, nowPlayingMoviesList.get(position).getId().toString());
                                                   bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, nowPlayingMoviesList.get(position).getTitle());
                                                   bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "movies");
                                                   mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                                                   mContext.startActivity(intent);
                                               }
                                           });

                                           tvMovieTitle.setText(nowPlayingMoviesList.get(position).getTitle());
                                           draweeView.setImageURI(getImageUri(nowPlayingMoviesList.get(position).getBackdropPath()));
                                           return itemView;
                                       }
                                   });
                                   carousel_view.setPageCount(nowPlayingMoviesList.size());
                                   carousel_view.setVisibility(View.VISIBLE);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   FirebaseCrash.report(e);

                                   Log.v("Exception", "NullPointerException");
                               }

                               @Override
                               public void onNext(Picture_Detail picture_detail) {
                                   nowPlayingMoviesList = picture_detail.getResults();
                               }
                           }

                );
    }

    private String getImageUri(String uri) {
        String IMAGE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w780";
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }

    private class MainPageThread extends Thread {
        final int requestType;

        MainPageThread(int requestType) {
            this.requestType = requestType;
        }

        @Override
        public void run() {
            super.run();
            if (threadAlreadyRunning) {
                return;
            }
            if (DateTimeHelper.getDifference(prefs.getLong("Last-Sync-Time-Movies", 0))) {
                fetchPopularDataFromServer();
                fetchTopRatedDataFromServer();
                fetchLatestDataFromServer();
                Date date = new Date();
                prefs.edit().putLong("Last-Sync-Time-Movies", date.getTime()).apply();
            } else {
                if (requestType == 1) {
                    threadAlreadyRunning = true;
                    if (popularMoviesList.isEmpty()) {
                        String popularMovieJSONList = prefs.getString("popularMoviesList", "");
                        popularMoviesList =
                                new Gson().fromJson(popularMovieJSONList, new TypeToken<List<Pictures>>() {
                                }.getType());
                        eventBus.post(new MessageEvent(1));

                    }

                    if (topRatedMoviesList.isEmpty()) {
                        String topRatedMovieJSONList = prefs.getString("topRatedMoviesList", "");
                        topRatedMoviesList =
                                new Gson().fromJson(topRatedMovieJSONList, new TypeToken<List<Pictures>>() {
                                }.getType());
                        eventBus.post(new MessageEvent(2));

                    }
                    if (upcomingMoviesList.isEmpty()) {
                        String upcomingMovieJSONList = prefs.getString("upcomingMoviesList", "");
                        if (!upcomingMovieJSONList.isEmpty()) {
                            upcomingMoviesList =
                                    new Gson().fromJson(upcomingMovieJSONList, new TypeToken<List<Pictures>>() {
                                    }.getType());
                            eventBus.post(new MessageEvent(3));
                        }
                    }
                }
            }

            fetchNowPlayingDataFromServer();


        }
    }
}


