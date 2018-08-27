package com.udacity.ahmed.mypopcorn.adapter;
/**
 * Created by Ahmed Hamdan.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.activity.EpisodeActivity;
import com.udacity.ahmed.mypopcorn.pojo.TvSeason.TvSeasonInfo;
import com.udacity.ahmed.mypopcorn.util.DateTimeHelper;

public class TvSeasonsEpisodeAdapter extends ArrayAdapter<TvSeasonInfo.Episode> {

    private final List<TvSeasonInfo.Episode> tvSeasonEpisodeList;
    private final LayoutInflater inflater;
    private final Context mContext;
    private final int tvId;

    public TvSeasonsEpisodeAdapter(Context context, List<TvSeasonInfo.Episode> objects, int id) {
        super(context, 0, objects);
        this.mContext = context;
        this.tvSeasonEpisodeList = objects;
        this.tvId = id;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (tvSeasonEpisodeList != null)
            return tvSeasonEpisodeList.size();
        else {
            return 0;
        }
    }

    @Override
    public TvSeasonInfo.Episode getItem(int position) {
        return tvSeasonEpisodeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TvSeasonInfo.Episode episode = tvSeasonEpisodeList.get(position);
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.episode_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.episodeTitle.setText(tvSeasonEpisodeList.get(position).getEpisodeNumber() + ". " + tvSeasonEpisodeList.get(position).getName());
        holder.episodeDesc.setText(tvSeasonEpisodeList.get(position).getOverview());
        holder.episode_date.setText(DateTimeHelper.parseDate(tvSeasonEpisodeList.get(position).getAirDate()));
        if (tvSeasonEpisodeList.get(position).getStillPath() != null && !tvSeasonEpisodeList.get(position).getStillPath().equals("")) {
            holder.draweeView.setVisibility(View.VISIBLE);
            holder.draweeView.setImageURI(getImageUri(tvSeasonEpisodeList.get(position).getStillPath()));
        } else {
            holder.draweeView.setVisibility(View.VISIBLE);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EpisodeActivity.class);
                intent.putExtra(EpisodeActivity.TV_ID, tvId);
                intent.putExtra(EpisodeActivity.SEASON_ID, episode.getSeasonNumber());
                intent.putExtra(EpisodeActivity.EPISODE_ID, episode.getEpisodeNumber());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    private String getImageUri(String uri) {
        String IMAGE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w342";
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }

    static class ViewHolder {
        final TextView episodeTitle;
        final TextView episodeDesc;
        final TextView episode_date;
        final SimpleDraweeView draweeView;
        final CardView cardView;

        ViewHolder(View itemView) {
            episodeTitle = (TextView) itemView.findViewById(R.id.episode_name);
            episode_date = (TextView) itemView.findViewById(R.id.episode_date);
            episodeDesc = (TextView) itemView.findViewById(R.id.episode_desc);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.img_episode_poster);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}