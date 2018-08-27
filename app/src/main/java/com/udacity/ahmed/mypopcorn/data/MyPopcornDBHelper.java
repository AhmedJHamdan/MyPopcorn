package com.udacity.ahmed.mypopcorn.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

import com.udacity.ahmed.mypopcorn.pojo.TvDetails.CombinedTvDetail;

/**
 * Created by Ahmed Hamdan.
 */

public class MyPopcornDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "StayTuned.db";


    public MyPopcornDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void addintoDB(CombinedTvDetail tvInformation, Context context, int tvId) {

        MyPopcornDBHelper dbHelper = new MyPopcornDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (!checkIfTvInfoExists(tvId, context)) {
            values.put(MyPopcornContract.StayTunedEntry.RELEASE_DATE, tvInformation.getFirstAirDate());
            values.put(MyPopcornContract.StayTunedEntry.RATING, tvInformation.getVoteCount());
            values.put(MyPopcornContract.StayTunedEntry.DESC, tvInformation.getOverview());
            values.put(MyPopcornContract.StayTunedEntry.SEASONS, tvInformation.getNumberOfSeasons());
            values.put(MyPopcornContract.StayTunedEntry.IMAGE, tvInformation.getBackdropPath());
            values.put(MyPopcornContract.StayTunedEntry.EPISODE_NO, 0);
            values.put(MyPopcornContract.StayTunedEntry.SEASON_NO, 0);
            values.put(MyPopcornContract.StayTunedEntry.IS_WATCHED, 0);
            values.put(MyPopcornContract.StayTunedEntry.IS_FAVORITE, 1);
            values.put(MyPopcornContract.StayTunedEntry.IS_NOTIFIED, 0);
            values.put(MyPopcornContract.StayTunedEntry.DATE_ADDED, new Date().toString());
            values.put(MyPopcornContract.StayTunedEntry.DATE_MODIFIED, new Date().toString());
            values.put(MyPopcornContract.StayTunedEntry.USER_ID, Math.random());
            values.put(MyPopcornContract.StayTunedEntry.TV_ID, tvId);
            values.put(MyPopcornContract.StayTunedEntry.NAME, tvInformation.getName());

            long rowUpdated = db.insert(MyPopcornContract.StayTunedEntry.TABLE_NAME, null, values);

            Log.v("Row Updated: ", rowUpdated + "");
        }
    }

    private static boolean checkIfTvInfoExists(int tvId, Context context) {
        MyPopcornDBHelper dbHelper = new MyPopcornDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String SQL_CHECK_TABLE = "Select " + MyPopcornContract.StayTunedEntry._ID + " from " + MyPopcornContract.StayTunedEntry.TABLE_NAME +
                " where " + MyPopcornContract.StayTunedEntry.TV_ID
                + " = " + tvId + "";
        Cursor cursor = db.rawQuery(SQL_CHECK_TABLE, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + MyPopcornContract.StayTunedEntry.TABLE_NAME + "("
                + MyPopcornContract.StayTunedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MyPopcornContract.StayTunedEntry.EPISODE_NO + " INTEGER NOT NULL,"
                + MyPopcornContract.StayTunedEntry.SEASON_NO + " INTEGER NOT NULL,"
                + MyPopcornContract.StayTunedEntry.IS_WATCHED + " INTEGER ,"
                + MyPopcornContract.StayTunedEntry.IS_FAVORITE + " INTEGER ,"
                + MyPopcornContract.StayTunedEntry.IS_NOTIFIED + " INTEGER ,"
                + MyPopcornContract.StayTunedEntry.DATE_ADDED + " DATE NOT NULL,"
                + MyPopcornContract.StayTunedEntry.DATE_MODIFIED + " DATE NOT NULL,"
                + MyPopcornContract.StayTunedEntry.USER_ID + " INTEGER ,"
                + MyPopcornContract.StayTunedEntry.RELEASE_DATE + " DATE ,"
                + MyPopcornContract.StayTunedEntry.RATING + " INTEGER,"
                + MyPopcornContract.StayTunedEntry.DESC + " TEXT,"
                + MyPopcornContract.StayTunedEntry.SEASONS + " INTEGER,"
                + MyPopcornContract.StayTunedEntry.IMAGE + " TEXT,"
                + MyPopcornContract.StayTunedEntry.NAME + " TEXT NOT NULL ,"
                + MyPopcornContract.StayTunedEntry.NEXT_AIR_DATE + " DATE ,"
                + MyPopcornContract.StayTunedEntry.TV_ID + " INTEGER NOT NULL)";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyPopcornContract.StayTunedEntry.TABLE_NAME);
        onCreate(db);
    }


    public void insertIntoDb(Date date, int tvId, Context context) {

        MyPopcornDBHelper dbHelper = new MyPopcornDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyPopcornContract.StayTunedEntry.NEXT_AIR_DATE, date.toString());
        db.update(MyPopcornContract.StayTunedEntry.TABLE_NAME, values, MyPopcornContract.StayTunedEntry.TV_ID + "=" + tvId, null);

    }
}
