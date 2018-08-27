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
import com.udacity.ahmed.mypopcorn.activity.TvDetailActivity;
import com.udacity.ahmed.mypopcorn.activity.ViewAllActivity;
import com.udacity.ahmed.mypopcorn.adapter.TvDataAdapter;
import com.udacity.ahmed.mypopcorn.data.MyPopcornContract;
import com.udacity.ahmed.mypopcorn.data.MyPopcornDBHelper;
import com.udacity.ahmed.mypopcorn.eventbus.MessageEvent;
import com.udacity.ahmed.mypopcorn.network.RetrofitManager;
import com.udacity.ahmed.mypopcorn.pojo.Picture.Pictures;
import com.udacity.ahmed.mypopcorn.pojo.Tv.Tv;
import com.udacity.ahmed.mypopcorn.pojo.TvDetails.CombinedTvDetail;
import com.udacity.ahmed.mypopcorn.util.Constants;
import com.udacity.ahmed.mypopcorn.util.DateTimeHelper;

public class TvShowFragment extends Fragment {

    private FirebaseAnalytics mFirebaseAnalytics;
    private EventBus eventBus;
    private SharedPreferences.Editor editor;
    private boolean threadAlreadyRunning = false;
    private RetrofitManager retrofitManager;
    private ArrayList<Pictures> topRatedTvList = new ArrayList<>();
    private ArrayList<Pictures> popularTvList = new ArrayList<>();
    private ArrayList<Pictures> airingTodayList = new ArrayList<>();
    private HorizontalGridView popularTvShowsHzGridView, topRatedTvshowsHzGridView, todayAirTvShowsHzGridView;
    private ProgressBar popularTvShowsProgressBar, topRatedTvShowsProgressBar, todayAirTvShowsProgressBar;
    private SharedPreferences prefs;
    private TextView popularTvShowHeading;
    private TextView topRatedTvshowHeading;
    private TextView todayAirTvTvShowHeading;
    private LinearLayout layout;
    private Context mContext;
    private List<CombinedTvDetail> dataList = new ArrayList<>();
    private CarouselView carousel_view;
    private List<CombinedTvDetail> tvInfoList;
    private AdView nativeView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        MobileAds.initialize(getContext(), Constants.ADUNIT);
        mContext = getActivity();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(mContext);
        new MyPopcornDBHelper(mContext);
        eventBus = EventBus.getDefault();

        carousel_view = (CarouselView) view.findViewById(R.id.carouselView);
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
                actionOnclick("airing_today");
            }
        });

        popularTvShowsHzGridView.setVisibility(View.INVISIBLE);
        topRatedTvshowsHzGridView.setVisibility(View.INVISIBLE);
        todayAirTvShowsHzGridView.setVisibility(View.INVISIBLE);
        new MainPageThread(1).start();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        eventBus.unregister(this);
    }


    @Override
    public void onStop() {
        eventBus.unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }


    private void fetchTopRatedDataFromServer() {
        Random rand = new Random();
        int pageToQuery = rand.nextInt(5) + 1;
        Observable<Tv> topRatedObservable = retrofitManager.listTvShows("top_rated", pageToQuery);

        topRatedObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Tv>() {
                               @Override
                               public void onCompleted() {
                                   if (topRatedTvList != null)
                                       eventBus.post(new MessageEvent(2));

                                   String topRatedTvJSONList = new Gson().toJson(topRatedTvList);
                                   SharedPreferences.Editor editor = prefs.edit();
                                   editor.putString("topRatedTvList", topRatedTvJSONList);
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
                               public void onNext(Tv tv) {
                                   topRatedTvList = tv.getTvShows();
                               }
                           }
                );


    }


    private void fetchPopularDataFromServer() {
        Random rand = new Random();
        int pageToQuery = rand.nextInt(5) + 1;
        Observable<Tv> popularObservable = retrofitManager.listTvShows("popular", pageToQuery);

        popularObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Tv>() {
                               @Override
                               public void onCompleted() {
                                   if (popularTvList != null)
                                       eventBus.post(new MessageEvent(1));
                                   String topRatedTvJSONList = new Gson().toJson(popularTvList);
                                   SharedPreferences.Editor editor = prefs.edit();
                                   editor.putString("popularTvList", topRatedTvJSONList);
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
                               public void onNext(Tv tv) {
                                   popularTvList = tv.getTvShows();
                               }
                           }

                );
    }

    private void fetchAiringTodayDataFromServer() {
        Observable<Tv> airingTodayObservable = retrofitManager.listTvShows("airing_today", 1);

        airingTodayObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Tv>() {
                               @Override
                               public void onCompleted() {
                                   if (airingTodayList != null)
                                       eventBus.post(new MessageEvent(3));
                                   String topRatedTvJSONList = new Gson().toJson(airingTodayList);
                                   SharedPreferences.Editor editor = prefs.edit();
                                   editor.putString("airingTodayList", topRatedTvJSONList);
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
                               public void onNext(Tv tv) {
                                   airingTodayList = tv.getTvShows();
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
            TvDataAdapter popularTvDataAdapter = new TvDataAdapter(getContext(), popularTvList, 1);
            popularTvShowsHzGridView.setAdapter(popularTvDataAdapter);
            popularTvDataAdapter.notifyDataSetChanged();
            popularTvShowsHzGridView.setVisibility(View.VISIBLE);
            popularTvShowsProgressBar.setVisibility(View.GONE);
            popularTvShowHeading.setText(R.string.popular_tv_heading);
        } else if (event.getRequest() == 2) {
            TvDataAdapter topRatedTvDataAdapter = new TvDataAdapter(getContext(), topRatedTvList, 2);
            topRatedTvshowsHzGridView.setAdapter(topRatedTvDataAdapter);
            topRatedTvDataAdapter.notifyDataSetChanged();
            topRatedTvshowsHzGridView.setVisibility(View.VISIBLE);
            topRatedTvShowsProgressBar.setVisibility(View.GONE);
            topRatedTvshowHeading.setText(R.string.top_rated_heading);
        } else if (event.getRequest() == 3) {
            TvDataAdapter todayAirTvDataAdapter = new TvDataAdapter(getContext(), airingTodayList, 1);
            todayAirTvShowsHzGridView.setAdapter(todayAirTvDataAdapter);
            todayAirTvDataAdapter.notifyDataSetChanged();
            todayAirTvShowsHzGridView.setVisibility(View.VISIBLE);
            todayAirTvShowsProgressBar.setVisibility(View.GONE);
            todayAirTvTvShowHeading.setText(R.string.air_today_heading);

        } else if (event.getRequest() == 4) {
            showSnakeBar(layout);
        } else if (event.getRequest() == 5) {
            carousel_view.setPageCount(tvInfoList.size());
            if (tvInfoList.size() >= 1) {
                carousel_view.setVisibility(View.VISIBLE);
            } else {
                carousel_view.setVisibility(View.GONE);
            }
        }
    }

    private void actionOnclick(String type) {
        Intent intent = new Intent(mContext, ViewAllActivity.class);
        intent.putExtra("listType", type);
        intent.putExtra("categoryType", 2);
        startActivity(intent);
    }

    private String getDataFromCursor(Cursor cursor, String Index) {
        return cursor.getString(cursor.getColumnIndex(Index));

    }

    private void readFromDatabase() {
        MyPopcornDBHelper dbHelper = new MyPopcornDBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        tvInfoList = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from " + MyPopcornContract.StayTunedEntry.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                CombinedTvDetail tvInfo = new CombinedTvDetail();
                tvInfo.setId(Integer.parseInt(getDataFromCursor(cursor, MyPopcornContract.StayTunedEntry.TV_ID)));
                tvInfo.setBackdropPath(getDataFromCursor(cursor, MyPopcornContract.StayTunedEntry.IMAGE));
                tvInfo.setName(getDataFromCursor(cursor, MyPopcornContract.StayTunedEntry.NAME));
                String is_fav = cursor.getString(cursor.getColumnIndex(MyPopcornContract.StayTunedEntry.IS_FAVORITE));

                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("is_fav" + "_" + tvInfo.getId(), Integer.parseInt(is_fav));
                editor.apply();

                tvInfoList.add(tvInfo);

            } while (cursor.moveToNext());
        }
        cursor.close();
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

    private void fetchFavoriteTvShowsFromDb() {
        try {
            readFromDatabase();

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
                            Intent intent = new Intent(mContext, TvDetailActivity.class)
                                    .putExtra(TvDetailActivityFragment.DETAIL_TV, tvInfoList.get(position).getId())
                                    .putExtra("ListToOpen", 2);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Bundle bundle = new Bundle();
                            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, tvInfoList.get(position).getId().toString());
                            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, tvInfoList.get(position).getName());
                            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "tv show");
                            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                            mContext.startActivity(intent);
                        }
                    });


                    tvMovieTitle.setText(tvInfoList.get(position).getName());
                    draweeView.setImageURI(getImageUri(tvInfoList.get(position).getBackdropPath()));
                    return itemView;
                }
            });

            eventBus.post(new MessageEvent(5));
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.report(e);

        }

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
            if (DateTimeHelper.getDifference(prefs.getLong("Last-Sync-Time", 0))) {
                fetchPopularDataFromServer();
                fetchTopRatedDataFromServer();
                fetchAiringTodayDataFromServer();
                fetchFavoriteTvShowsFromDb();
                Date date = new Date();
                prefs.edit().putLong("Last-Sync-Time", date.getTime()).apply();
            } else {
                if (requestType == 1) {
                    threadAlreadyRunning = true;
                    if (popularTvList.isEmpty()) {
                        String popularTvJSONList = prefs.getString("popularTvList", "");
                        popularTvList =
                                new Gson().fromJson(popularTvJSONList, new TypeToken<List<Pictures>>() {
                                }.getType());
                        eventBus.post(new MessageEvent(1));

                    }

                    if (topRatedTvList.isEmpty()) {
                        String topRatedTvJSONList = prefs.getString("topRatedTvList", "");
                        topRatedTvList =
                                new Gson().fromJson(topRatedTvJSONList, new TypeToken<List<Pictures>>() {
                                }.getType());
                        eventBus.post(new MessageEvent(2));

                    }
                    if (airingTodayList.isEmpty()) {
                        String airingTodayJSONList = prefs.getString("airingTodayList", "");
                        if (!airingTodayJSONList.isEmpty()) {
                            airingTodayList =
                                    new Gson().fromJson(airingTodayJSONList, new TypeToken<List<Pictures>>() {
                                    }.getType());
                            eventBus.post(new MessageEvent(3));
                        }
                    }
                    fetchFavoriteTvShowsFromDb();

                }
            }

        }
    }
}


