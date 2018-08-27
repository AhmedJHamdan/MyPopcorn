package com.udacity.ahmed.mypopcorn.adapter;
/**
 * Created by Ahmed Hamdan.
 */


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.activity.TvDetailActivity;
import com.udacity.ahmed.mypopcorn.fragment.TvDetailActivityFragment;

public class TvInfoCursorAdapter extends CursorAdapter {


    private final Context mContext;

    public TvInfoCursorAdapter(Context context, Cursor c) {
        super(context, c);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fav_movie_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        TextView tvMovieTitle;
        SimpleDraweeView draweeView;
        CardView cardView;

        tvMovieTitle = (TextView) view.findViewById(R.id.tv_movie_title);
        draweeView = (SimpleDraweeView) view.findViewById(R.id.img_movie_poster);
        cardView = (CardView) view.findViewById(R.id.card_view);


        tvMovieTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        draweeView.setImageURI(getImageUri(cursor.getString(cursor.getColumnIndexOrThrow("image"))));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TvDetailActivity.class)
                        .putExtra(TvDetailActivityFragment.DETAIL_TV, cursor.getInt(cursor.getColumnIndexOrThrow("tv_id")))
                        .putExtra("ListToOpen",2);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    private String getImageUri(String uri) {
        String IMAGE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w342";
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }


}
