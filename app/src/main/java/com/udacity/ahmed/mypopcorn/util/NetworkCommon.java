package com.udacity.ahmed.mypopcorn.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.firebase.crash.FirebaseCrash;

/**
 * Created by Ahmed Hamdan.
 */

class NetworkCommon {

    public static boolean isConnected(Context context) {
        try {
            final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED;
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.report(e);

        }
        return true;
    }
}
