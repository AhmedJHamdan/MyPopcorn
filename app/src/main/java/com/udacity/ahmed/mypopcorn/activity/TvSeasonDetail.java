package com.udacity.ahmed.mypopcorn.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.google.firebase.crash.FirebaseCrash;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.adapter.TvSeasonsEpisodeAdapter;
import com.udacity.ahmed.mypopcorn.eventbus.MessageEvent;
import com.udacity.ahmed.mypopcorn.network.RetrofitManager;
import com.udacity.ahmed.mypopcorn.pojo.TvSeason.TvSeasonInfo;

public class TvSeasonDetail extends AppCompatActivity {


    public static final String TAG = TvSeasonDetail.class.getSimpleName();
    public static final String TV_ID = "TV_ID";
    public static final String SEASON_ID = "SEASON_ID";

    private EventBus eventBus;
    private TvSeasonInfo tvSeasonInfo = new TvSeasonInfo();
    private int tvId;
    private RetrofitManager retrofitManager;
    private boolean threadAlreadyRunning;
    private GridView tvSeasonsGridView;
    private ProgressBar tvSeasonsProgressBar;
    private int seasonId;

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

    private void hideProgressBar() {
        tvSeasonsGridView.setVisibility(View.VISIBLE);
        tvSeasonsProgressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        tvSeasonsGridView.setVisibility(View.GONE);
        tvSeasonsProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_season_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvId = (int) getIntent().getExtras().get(TV_ID);
        seasonId = (int) getIntent().getExtras().get(SEASON_ID);
        toolbar.setTitle("Season:" + seasonId);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        retrofitManager = RetrofitManager.getInstance();
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

        tvSeasonsGridView = (GridView) findViewById(R.id.list_view);
        tvSeasonsProgressBar = (ProgressBar) findViewById(R.id.progress_main);
        showProgressBar();
        new LoadSeasonInfoThread(1).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getRequest() == 1) {
            hideProgressBar();
            threadAlreadyRunning = false;
            TvSeasonsEpisodeAdapter tvSeasonsEpisodeAdapter = new TvSeasonsEpisodeAdapter(this, tvSeasonInfo.getEpisodes(), tvId);
            tvSeasonsGridView.setAdapter(tvSeasonsEpisodeAdapter);
            tvSeasonsEpisodeAdapter.notifyDataSetChanged();
            tvSeasonsGridView.setVisibility(View.VISIBLE);
            tvSeasonsProgressBar.setVisibility(View.GONE);
        }
    }

    private class LoadSeasonInfoThread extends Thread {
        final int requestType;

        LoadSeasonInfoThread(int requestType) {
            this.requestType = requestType;
        }

        @Override
        public void run() {
            super.run();
            if (threadAlreadyRunning) {
            } else {
                threadAlreadyRunning = true;
                Observable<TvSeasonInfo> tvSeasonInfoObservable = retrofitManager.getTvSeasonInfo(tvId + "", seasonId + "");
                tvSeasonInfoObservable
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Subscriber<TvSeasonInfo>() {
                                       @Override
                                       public void onCompleted() {
                                           if (tvSeasonInfo != null) {
                                               eventBus.post(new MessageEvent(1));
                                           }
                                       }

                                       @Override
                                       public void onError(Throwable e) {
                                           e.printStackTrace();
                                           FirebaseCrash.report(e);
                                       }

                                       @Override
                                       public void onNext(TvSeasonInfo tvInfo) {
                                           tvSeasonInfo = tvInfo;
                                       }
                                   }
                        );

            }

        }
    }
}


