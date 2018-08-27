package com.udacity.ahmed.mypopcorn.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.crash.FirebaseCrash;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.eventbus.MessageEvent;
import com.udacity.ahmed.mypopcorn.fragment.ImageFragment;
import com.udacity.ahmed.mypopcorn.fragment.TvDetailActivityFragment;
import com.udacity.ahmed.mypopcorn.fragment.VideosFragment;
import com.udacity.ahmed.mypopcorn.network.RetrofitManager;
import com.udacity.ahmed.mypopcorn.pojo.CombinedMovieDetail;
import com.udacity.ahmed.mypopcorn.pojo.Images.Poster;
import com.udacity.ahmed.mypopcorn.pojo.TvDetails.CombinedTvDetail;
import com.udacity.ahmed.mypopcorn.pojo.TvSeason.TvSeasonInfo;
import com.udacity.ahmed.mypopcorn.pojo.Video.VideoResult;
import com.udacity.ahmed.mypopcorn.util.Constants;

public class TvDetailActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    CombinedTvDetail tvInformation;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ShowsFragmentPagerAdapter showsPagerAdapter;
    private SharedPreferences prefs;
    private ArrayList<String> imagesList;
    private int tvId;
    private boolean threadAlreadyRunning = false;
    private TvSeasonInfo.Episode episode;
    private RetrofitManager retrofitManager;
    private EventBus eventBus;
    private CombinedMovieDetail combinedMovieDetail;
    private CollapsingToolbarLayout collapsingToolbar;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progress_detail);
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutHome);
        viewPager = (ViewPager) findViewById(R.id.pager);
        retrofitManager = RetrofitManager.getInstance();
        progressBar.setVisibility(View.VISIBLE);
        tvId = (int) getIntent().getExtras().get(TvDetailActivityFragment.DETAIL_TV);

        prefs = this.getSharedPreferences(
                Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);

        new LoadDetailPageThread(1).start();
    }

    private void initializeAdapter() {
        showsPagerAdapter = new ShowsFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(showsPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        tabLayout.getTabAt(0).select();
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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        try {
            loadImages(tvInformation);
            initializeAdapter();
            progressBar.setVisibility(View.GONE);
            showsPagerAdapter.notifyDataSetChanged();
            viewPager.setCurrentItem(0);
            tabLayout.getTabAt(0).select();
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.report(e);

        }

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
        eventBus.unregister(this);
    }

    private void fetchDataForTvInfo() {
        Observable<CombinedTvDetail> tvInfoObservable = retrofitManager.getTvInfo(tvId + "");
        tvInfoObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<CombinedTvDetail>() {
                               @Override
                               public void onCompleted() {
                                   if (tvInformation != null) {

                                       String tvInformationJSONList = new Gson().toJson(tvInformation);
                                       SharedPreferences.Editor editor = prefs.edit();
                                       editor.putString("tvInformation_" + tvId, tvInformationJSONList);
                                       editor.apply();

                                       Integer lastSeasonNumber = null;
                                       if (tvInformation.getStatus().equals("Returning Series")) {
                                           lastSeasonNumber = tvInformation.getNumberOfSeasons();
                                       }
                                       if (lastSeasonNumber != null) {
                                           Intent intent = new Intent(getApplicationContext(), NextAirService.class);
                                           intent.putExtra("tvId", tvId);
                                           intent.putExtra("lastSeasonNumber", lastSeasonNumber);
                                           intent.putExtra("TvSeriesName", tvInformation.getName());
                                           startService(intent);

                                       }
                                       eventBus.post(new MessageEvent(1));

                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   FirebaseCrash.report(e);
                                   Log.v("Exception", Arrays.toString(e.getStackTrace()));
                               }

                               @Override
                               public void onNext(CombinedTvDetail combinedTvDetail) {
                                   tvInformation = combinedTvDetail;
                               }
                           }
                );
    }

    private void loadImages(CombinedTvDetail tvInformation) {
        List<Poster> images = tvInformation.getImages().getPosters();
        imagesList = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            imagesList.add(images.get(i).getFilePath());
        }
    }

    private class LoadDetailPageThread extends Thread {
        final int requestType;

        LoadDetailPageThread(int requestType) {
            this.requestType = requestType;
        }

        @Override
        public void run() {
            super.run();
            if (threadAlreadyRunning) {
            } else {
                threadAlreadyRunning = true;

                try {
                    String tvInformationJSONList = prefs.getString("tvInformation_" + tvId, "");
                    if (prefs.contains("tvInformation_" + tvId)) {
                        tvInformation =
                                new Gson().fromJson(tvInformationJSONList, new TypeToken<CombinedTvDetail>() {
                                }.getType());
                        if (tvInformation != null) {
                            eventBus.post(new MessageEvent(1));
                        } else {
                            fetchDataForTvInfo();
                        }
                    } else {
                        fetchDataForTvInfo();

                    }
                } catch (Exception e) {
                    FirebaseCrash.report(e);
                    e.printStackTrace();
                }

            }
        }
    }


    private class ShowsFragmentPagerAdapter extends FragmentStatePagerAdapter {
        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{"Detail", "Videos", "Images"};
        private Context context;

        public ShowsFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TvDetailActivityFragment.newInstance(tvInformation);
                case 1:
                    return VideosFragment.newInstance((ArrayList<VideoResult>) tvInformation.getVideos().getResults());
                case 2:
                    return ImageFragment.newInstance(imagesList);
                default:
                    return TvDetailActivityFragment.newInstance(tvInformation);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
