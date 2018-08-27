package com.udacity.ahmed.mypopcorn.adapter;
/**
 * Created by Ahmed Hamdan.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.activity.TvSeasonDetail;
import com.udacity.ahmed.mypopcorn.pojo.TvDetails.CombinedTvDetail;

public class TvSeasonsAdapter extends RecyclerView.Adapter<TvSeasonsAdapter.ViewHolder> {

    private final Context mContext;
    private List<CombinedTvDetail.Season> tvSeasonList;
    private LayoutInflater inflater;
    private int tvId;


    public TvSeasonsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public TvSeasonsAdapter(Context context, List<CombinedTvDetail.Season> objects, int id) {
        this.mContext = context;
        this.tvSeasonList = objects;
        this.tvId = id;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public TvSeasonsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.movie_layout, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final TvSeasonsAdapter.ViewHolder holder, int position) {

        holder.tvMovieTitle.setText(mContext.getString(R.string.season) + tvSeasonList.get(position).getSeasonNumber().toString());
        holder.draweeView.setImageURI(getImageUri(tvSeasonList.get(position).getPosterPath()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TvSeasonDetail.class);
                intent.putExtra(TvSeasonDetail.TV_ID, tvId);
                intent.putExtra(TvSeasonDetail.SEASON_ID, tvSeasonList.get(holder.getAdapterPosition()).getSeasonNumber());
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
        if (tvSeasonList != null)
            return tvSeasonList.size();
        else
            return 0;
    }


    public String getImageUri(String uri) {
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