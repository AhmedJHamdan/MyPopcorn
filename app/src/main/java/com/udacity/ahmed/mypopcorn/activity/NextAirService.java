package com.udacity.ahmed.mypopcorn.activity;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.network.RetrofitManager;
import com.udacity.ahmed.mypopcorn.pojo.TvSeason.TvSeasonInfo;
import com.udacity.ahmed.mypopcorn.util.Constants;
import com.udacity.ahmed.mypopcorn.util.DateTimeHelper;

/**
 * Created by Ahmed Hamdan.
 */

public class NextAirService extends IntentService {
    private final RetrofitManager retrofitManager;
    private final Context context;
    private int tvId;
    private Integer lastSeasonNumber;
    private TvSeasonInfo tvSeasonInfo;
    private List<TvSeasonInfo.Episode> episodeList;
    private String json;
    private SharedPreferences prefs;
    private String tvSeriesName;


    public NextAirService() {
        super("NextAirService");
        retrofitManager = RetrofitManager.getInstance();
        context = this;

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            tvId = intent != null ? intent.getIntExtra("tvId", 0) : 0;
            lastSeasonNumber = intent != null ? intent.getIntExtra("lastSeasonNumber", 0) : 0;
            tvSeriesName = intent != null ? intent.getStringExtra("TvSeriesName") : "";
            if (tvId != 0 && lastSeasonNumber != 0) {
                fetchDataForSeasonInfo();
            }
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.report(e);

        }

    }

    private void fetchDataForSeasonInfo() {
        Observable<TvSeasonInfo> tvSeasonInfoObservable = retrofitManager.getTvSeasonInfo(tvId + "", lastSeasonNumber + "");
        tvSeasonInfoObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<TvSeasonInfo>() {
                               @Override
                               public void onCompleted() {
                                   if (tvSeasonInfo != null) {
                                       episodeList = tvSeasonInfo.getEpisodes();
                                       if (episodeList != null && episodeList.size() > 0) {
                                           for (TvSeasonInfo.Episode episode : episodeList) {
                                               if (episode.getAirDate() != null) {
                                                   try {
                                                       //                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd").parse(episode.getAirDate().toString());
                                                       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                                       Date date = simpleDateFormat.parse(episode.getAirDate());
                                                       Date currentDate = new Date();
                                                       if (date.getTime() > currentDate.getTime()) {
                                                           Log.v("Next Air date", date.toString());
                                                           SharedPreferences prefs = context.getSharedPreferences(
                                                                   Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
                                                           final int is_fav = prefs.getInt("is_fav" + "_" + tvId, 0);
//                                                       if (is_fav == 1) {
//                                                           new MyPopcornDBHelper(context).insertIntoDb(currentDate, tvId, context);
//                                                       }

                                                           Gson gson = new Gson();
                                                           json = gson.toJson(episode);
                                                           SharedPreferences.Editor editor = prefs.edit();
                                                           editor.putString(Constants.NEXT_AIR_DATE + "_" + tvId, json);
                                                           editor.apply();
                                                           buildNotification();

                                                       }


                                                   } catch (ParseException e) {
                                                       e.printStackTrace();
                                                       FirebaseCrash.report(e);

                                                   }
                                               }
                                           }
                                       }
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

    private void buildNotification() {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

        JSONObject episodeJson = null;
        try {
            episodeJson = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            FirebaseCrash.report(e);

        }
        TvSeasonInfo.Episode episode = new Gson().fromJson(episodeJson != null ? episodeJson.toString() : null, TvSeasonInfo.Episode.class);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setAutoCancel(true);
        mBuilder.setContentTitle("Next episode of " + tvSeriesName + " arrives on ");
        mBuilder.setContentText(DateTimeHelper.parseDate(episode.getAirDate()));
        mBuilder.setColor(Color.parseColor("#3DA0E9"));
        mBuilder.setSmallIcon(R.drawable.fav);

        notificationManager.notify(1, mBuilder.build());
    }
}




