package com.udacity.ahmed.mypopcorn.activity;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.adapter.SearchEpisodeAdapter;
import com.udacity.ahmed.mypopcorn.eventbus.MessageEvent;
import com.udacity.ahmed.mypopcorn.network.RetrofitManager;
import com.udacity.ahmed.mypopcorn.pojo.Search.SearchResult;

public class SearchResultsActivity extends AppCompatActivity {

    private EventBus eventBus;
    private RetrofitManager retrofitManager;
    private List<SearchResult.Result> searchResultDataList;
    private GridView sarchResultGridView;
//    private TextView sarchResultHeading;
    private ProgressBar sarchResultProgressBar;

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
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Search Results:");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        retrofitManager = RetrofitManager.getInstance();
        sarchResultGridView = (GridView) findViewById(R.id.list_view);
//        sarchResultHeading = (TextView) findViewById(R.id.heading);
        sarchResultProgressBar = (ProgressBar) findViewById(R.id.progress_main);
        sarchResultProgressBar.setVisibility(View.VISIBLE);
        try {

            new SearchThread().start();
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.report(e);

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        try {
            SearchEpisodeAdapter searchEpisodeAdapter = new SearchEpisodeAdapter(this, searchResultDataList, 0);
            sarchResultGridView.setAdapter(searchEpisodeAdapter);
            searchEpisodeAdapter.notifyDataSetChanged();
            sarchResultGridView.setVisibility(View.VISIBLE);
            sarchResultProgressBar.setVisibility(View.GONE);
//            sarchResultHeading.setText("Search Video ");
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.report(e);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SearchThread extends Thread {

        @Override
        public void run() {
            super.run();

            try {
                Observable<SearchResult> popularObservable = retrofitManager.searchTvShows( getIntent().getStringExtra("search"));

                popularObservable
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Subscriber<SearchResult>() {
                                       @Override
                                       public void onCompleted() {
                                         eventBus.post(new MessageEvent(1));
                                       }

                                       @Override
                                       public void onError(Throwable e) {
                                           e.printStackTrace();
                                           FirebaseCrash.report(e);

                                       }

                                       @Override
                                       public void onNext(SearchResult searchResult) {
                                           searchResultDataList=new ArrayList<>();
                                           searchResultDataList.clear();
                                           searchResultDataList.addAll(searchResult.getResults());

                                       }
                                   }

                        );
            } catch (Exception e) {
                e.printStackTrace();
                FirebaseCrash.report(e);

            }
        }
    }
}
