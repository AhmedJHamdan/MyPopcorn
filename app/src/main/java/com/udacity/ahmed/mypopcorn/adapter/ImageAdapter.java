package com.udacity.ahmed.mypopcorn.adapter;
/**
 * Created by Ahmed Hamdan.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import com.udacity.ahmed.mypopcorn.R;

public class ImageAdapter extends ArrayAdapter<String> {

    private ArrayList<String> movieArrayList;
    private LayoutInflater inflater;

    public ImageAdapter(Context context, ArrayList<String> objects) {
        super(context, 0, objects);
        Context mContext = context;
        this.movieArrayList = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (movieArrayList != null)
            return movieArrayList.size();
        else
            return 0;
    }

    @Override
    public String getItem(int position) {
        return movieArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String movie = movieArrayList.get(position);
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.image_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.draweeView.setImageURI(getImageUri(movie));


        return convertView;
    }

    public String getImageUri(String uri) {
        String IMAGE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w342";
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }

    static class ViewHolder {

        SimpleDraweeView draweeView;

        public ViewHolder(View itemView) {
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.image_view);
        }
    }
}