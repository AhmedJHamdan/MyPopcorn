package com.udacity.ahmed.mypopcorn.adapter;
/**
 * Created by Ahmed Hamdan.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.activity.MovieDetailActivity;
import com.udacity.ahmed.mypopcorn.fragment.MovieDetailActivityFragment;
import com.udacity.ahmed.mypopcorn.pojo.Picture.Pictures;

public class MovieListDataAdapter extends RecyclerView.Adapter<MovieListDataAdapter.ViewHolder> {

    private final int value;
    private final List<Pictures> picturesList;
    private final LayoutInflater inflater;
    private final Context mContext;
    private final FirebaseAnalytics mFirebaseAnalytics;

    public MovieListDataAdapter(Context context, List<Pictures> objects, int value) {
        this.mContext = context;
        this.picturesList = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        this.value = value;
    }


    @Override
    public MovieListDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView;
        if (value == 1) {
            convertView = inflater.inflate(R.layout.movie_layout, parent, false);
        } else {
            convertView = inflater.inflate(R.layout.custom_movie_layout, parent, false);
        }
        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(final MovieListDataAdapter.ViewHolder holder,  int position) {
        holder.tvMovieTitle.setText(picturesList.get(position).getTitle());
        holder.draweeView.setImageURI(getImageUri(picturesList.get(position).getPosterPath()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class)
                        .putExtra(MovieDetailActivityFragment.DETAIL_TV, picturesList.get(holder.getAdapterPosition()).getId())
                        .putExtra("ListToOpen", 1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, picturesList.get(holder.getAdapterPosition()).getId().toString());
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, picturesList.get(holder.getAdapterPosition()).getTitle());
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "movies");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (picturesList != null)
            return picturesList.size();
        else {
            return 0;
        }
    }


    private String getImageUri(String uri) {
        String IMAGE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w342";
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvMovieTitle;
        final CardView cardView;
        final SimpleDraweeView draweeView;

        ViewHolder(View itemView) {
            super(itemView);
            tvMovieTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.img_movie_poster);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}