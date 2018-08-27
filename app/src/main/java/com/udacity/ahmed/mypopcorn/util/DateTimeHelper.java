package com.udacity.ahmed.mypopcorn.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Ahmed Hamdan.
 */

public class DateTimeHelper {


    public static String parseDate(String dateTime) {
        try {
            if (dateTime != null && !dateTime.isEmpty() && !dateTime.equalsIgnoreCase("")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                SimpleDateFormat format2 = new SimpleDateFormat("dd, MMM yyyy", Locale.ENGLISH);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
                Date date = simpleDateFormat.parse(dateTime);

                return format2.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static boolean getDifference(Long hours) {

        if (hours == 0) {
            return true;
        }
        Date date = new Date(hours);
        Date dt2 = new Date();

        long diff = dt2.getTime() - date.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((dt2.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));

        if (diffInDays > 1) {
            Log.v("Diff in number of days" , diffInDays+"");
            return true;
        } else if (diffHours > 3) {
            Log.v("Diff in number of hours" , diffInDays+"");
            return true;
        }
        return false;
    }

}
