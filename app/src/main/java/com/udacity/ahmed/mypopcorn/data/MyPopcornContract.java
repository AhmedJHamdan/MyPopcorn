package com.udacity.ahmed.mypopcorn.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ahmed Hamdan.
 */

public class MyPopcornContract {

    private static final String CONTENT_AUTHORITY = "com.udacity.ahmed.mypopcorn";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final String PATH_TV = "stay_tuned";

    public static final class StayTunedEntry implements BaseColumns {

        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TV;

        public static final String TABLE_NAME = "stay_tuned";
        public static final String _ID = BaseColumns._ID;
        public static final String EPISODE_NO = "episode_no";
        public static final String SEASON_NO = "season_no";
        public static final String IS_WATCHED = "is_watched";
        public static final String IS_FAVORITE = "is_favorite";
        public static final String IS_NOTIFIED = "is_notified";
        public static final String DATE_ADDED = "date_added";
        public static final String DATE_MODIFIED = "date_modied";
        public static final String USER_ID = "user_id";
        public static final String TV_ID = "tv_id";
        public static final String RELEASE_DATE = "release_date";
        public static final String RATING = "rating";
        public static final String DESC = "desc";
        public static final String SEASONS = "seasons";
        public static final String IMAGE = "image";
        public static final String NAME = "name";
        public static final String NEXT_AIR_DATE = "next_air_date";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TV).build();

        public static Uri buildTvUri() {
            return CONTENT_URI.buildUpon().build();
        }
    }
}