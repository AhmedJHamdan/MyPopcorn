package com.udacity.ahmed.mypopcorn.adapter;
/**
 * Created by Ahmed Hamdan.
 */


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.pojo.Cast.Cast;

public class TvCastInfoAdapter extends RecyclerView.Adapter<TvCastInfoAdapter.ViewHolder> {

    private List<Cast> tvCastList;
    private LayoutInflater inflater;
    private final Context mContext;


    public TvCastInfoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public TvCastInfoAdapter(Context context, List<Cast> objects, int id) {
        this.mContext = context;
        this.tvCastList = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public TvCastInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.movie_layout, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(TvCastInfoAdapter.ViewHolder holder, final int position) {

        holder.tvMovieTitle.setText(tvCastList.get(position).getOriginalTitle());
        if (tvCastList.get(position).getPosterPath()!=null)
        holder.draweeView.setImageURI(getImageUri(tvCastList.get(position).getPosterPath()));


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (tvCastList != null)
            return tvCastList.size();
        else
            return 0;
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