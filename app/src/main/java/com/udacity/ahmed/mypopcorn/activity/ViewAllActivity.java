package com.udacity.ahmed.mypopcorn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.google.firebase.crash.FirebaseCrash;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.adapter.MoviesInfoAdapter;
import com.udacity.ahmed.mypopcorn.adapter.TvInfoAdapter;
import com.udacity.ahmed.mypopcorn.eventbus.MessageEvent;
import com.udacity.ahmed.mypopcorn.network.RetrofitManager;
import com.udacity.ahmed.mypopcorn.pojo.Picture.Picture_Detail;
import com.udacity.ahmed.mypopcorn.pojo.Picture.Pictures;
import com.udacity.ahmed.mypopcorn.pojo.Tv.Tv;

public class ViewAllActivity extends AppCompatActivity {

    private boolean threadRunning = false;
    private ArrayList<Pictures> tvInfoList;
    private ArrayList<Pictures> moviesList;
    private EventBus eventBus;
    private RetrofitManager retrofitManager;
    private int page = 1;
    private TvInfoAdapter tvInfoAdapter;
    private MoviesInfoAdapter moviesInfoAdapter;
    private GridView myGrid;
    private boolean drawnPrevious = false;
    private String listType;
    private int categoryType;
    private Parcelable state;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("View All");
        myGrid = (GridView) findViewById(R.id.grid_view);
        retrofitManager = RetrofitManager.getInstance();
        setSupportActionBar(toolbar);
        tvInfoList = new ArrayList<>();
        moviesList = new ArrayList<>();
        progressbar = (ProgressBar) findViewById(R.id.progress_fragment);
        showProgressBar();
        Intent intent = getIntent();
        listType = intent.getStringExtra("listType");
        categoryType = intent.getIntExtra("categoryType", 1);

        tvInfoAdapter = new TvInfoAdapter(getApplicationContext(), tvInfoList);
        moviesInfoAdapter = new MoviesInfoAdapter(getApplicationContext(), moviesList);


        if (categoryType == 2) {
            myGrid.setAdapter(tvInfoAdapter);
        } else if (categoryType == 1) {
            myGrid.setAdapter(moviesInfoAdapter);
        }

        myGrid.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((firstVisibleItem + visibleItemCount >= totalItemCount) && firstVisibleItem != 0 && drawnPrevious) {
                    // End has been reached
                    new ViewAllThread(categoryType, true).start();

                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });


        new ViewAllThread(categoryType).start();
        drawnPrevious = true;

    }

    private void hideProgressBar() {
        myGrid.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        myGrid.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onStart() {
        super.onStart();
        eventBus = EventBus.getDefault();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    private void fetchTvDataFromServer(String listType, int page, final boolean scroll) {
        Observable<Tv> popularObservable = retrofitManager.listTvShows(listType, page);

        popularObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Tv>() {
                               @Override
                               public void onCompleted() {
                                   if (tvInfoList != null) {
                                       if (scroll) {
                                           eventBus.post(new MessageEvent(3));
                                       } else {
                                           eventBus.post(new MessageEvent(1));
                                       }
                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   FirebaseCrash.report(e);

                                   Log.v("Exception", "NullPointerException");
                               }

                               @Override
                               public void onNext(Tv tv) {
                                   tvInfoList.addAll(tv.getTvShows());
                               }
                           }

                );
    }

    private void fetchMoviesDataFromServer(String listType, int page, final boolean scroll) {
        Observable<Picture_Detail> popularObservable = retrofitManager.listMoviesInfo(listType, page);

        popularObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Picture_Detail>() {
                               @Override
                               public void onCompleted() {
                                   if (moviesList != null) {
                                       if (scroll) {
                                           eventBus.post(new MessageEvent(4));
                                       } else {
                                           eventBus.post(new MessageEvent(2));
                                       }
                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   FirebaseCrash.report(e);

                                   Log.v("Exception", "NullPointerException");
                               }

                               @Override
                               public void onNext(Picture_Detail picture_detail) {
                                   moviesList.addAll(picture_detail.getResults());
                               }
                           }

                );
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        hideProgressBar();
        if (event.getRequest() == 1) {
            tvInfoAdapter.notifyDataSetChanged();
        } else if (event.getRequest() == 2) {
            moviesInfoAdapter.notifyDataSetChanged();
        } else if (event.getRequest() == 3) {
            tvInfoAdapter.notifyDataSetChanged();
        } else {
            moviesInfoAdapter.notifyDataSetChanged();
        }
        threadRunning = false;
    }

    private class ViewAllThread extends Thread {
        final int request;
        boolean scroll;

        public ViewAllThread(int request) {
            this.request = request;
        }

        public ViewAllThread(int request, boolean scroll) {
            this.request = request;
            this.scroll = scroll;
        }

        @Override
        public void run() {
            super.run();
            if (threadRunning) {
                return;
            }
            threadRunning = true;
            if (request == 1) {
                fetchMoviesDataFromServer(listType, page++, scroll);
            } else {
                fetchTvDataFromServer(listType, page++, scroll);
            }
        }
    }
}
