package com.udacity.ahmed.mypopcorn.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
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
import com.udacity.ahmed.mypopcorn.pojo.Episode.EpisodeInfo;

public class EpisodeActivity extends AppCompatActivity {


    public static final String TAG = TvSeasonDetail.class.getSimpleName();
    public static final String TV_ID = "TV_ID";
    public static final String SEASON_ID = "SEASON_ID";
    public static final String EPISODE_ID = "EPISODE_ID";
    Button fav_button;
    private EventBus eventBus;
    private EpisodeInfo episodeInfo = new EpisodeInfo();
    private int tvId;
    private RetrofitManager retrofitManager;
    private TextView releaseDate;
    private TextView plotSynopsis;
    private SimpleDraweeView draweeView;
    private CollapsingToolbarLayout collapsingToolbar;
    private boolean threadAlreadyRunning;
    private GridView tvSeasonsGridView;
    private TvSeasonsEpisodeAdapter tvSeasonsEpisodeAdapter;
    private ProgressBar tvSeasonsProgressBar;
    private TextView tvSeasonsHeading;
    private int seasonId;
    private String episodeId;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        retrofitManager = RetrofitManager.getInstance();
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);


        TextView title = (TextView) findViewById(R.id.title);
        releaseDate = (TextView) findViewById(R.id.release_date);
        TextView vote = (TextView) findViewById(R.id.vote);
        plotSynopsis = (TextView) findViewById(R.id.plot_synopsis);
//        fav_button = (Button) findViewById(R.id.b11);
        draweeView = (SimpleDraweeView) findViewById(R.id.movie_poster);


//        tvSeasonsGridView = (GridView) findViewById(R.id.episodesCard).findViewById(R.id.list_view);
//        tvSeasonsHeading = (TextView) findViewById(R.id.episodesCard).findViewById(R.id.heading);
//        tvSeasonsProgressBar = (ProgressBar) findViewById(R.id.episodesCard).findViewById(R.id.progress_main);
//        tvSeasonsProgressBar.setVisibility(View.VISIBLE);

//        ViewCompat.setNestedScrollingEnabled(tvSeasonsGridView, true);


        tvId = (int) getIntent().getExtras().get(TV_ID);
        seasonId = (int) getIntent().getExtras().get(SEASON_ID);
        episodeId = getIntent().getExtras().get(EPISODE_ID).toString();


//            collapsingToolbar.setTitle(picture.getTitle());

        new LoadEpisodeInfoThread(1).start();
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
            threadAlreadyRunning = false;
            collapsingToolbar.setTitle(episodeInfo.getName());
            draweeView.setImageURI(getString(R.string.image_path)+ episodeInfo.getStillPath());
            releaseDate.setText(getString(R.string.episode_air_heading) + episodeInfo.getAirDate() + "");
            plotSynopsis.setText(episodeInfo.getOverview());
        }
    }

    private class LoadEpisodeInfoThread extends Thread {
        final int requestType;

        LoadEpisodeInfoThread(int requestType) {
            this.requestType = requestType;
        }

        @Override
        public void run() {
            super.run();
            if (threadAlreadyRunning) {
            } else {
                threadAlreadyRunning = true;
                Observable<EpisodeInfo> tvSeasonInfoObservable = retrofitManager.getEpisodeInfo(tvId + "", seasonId + "", episodeId);
                tvSeasonInfoObservable
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Subscriber<EpisodeInfo>() {
                                       @Override
                                       public void onCompleted() {
                                           if (episodeInfo != null) {
                                               eventBus.post(new MessageEvent(1));
                                           }
                                       }

                                       @Override
                                       public void onError(Throwable e) {
                                           e.printStackTrace();
                                           FirebaseCrash.report(e);
                                       }

                                       @Override
                                       public void onNext(EpisodeInfo einfo) {
                                           episodeInfo = einfo;
                                       }
                                   }
                        );

            }

        }
    }
}


