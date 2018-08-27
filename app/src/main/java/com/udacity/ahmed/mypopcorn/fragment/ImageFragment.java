package com.udacity.ahmed.mypopcorn.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.adapter.ImageAdapter;

public class ImageFragment extends Fragment {

    ArrayList<String> values;

    public static ImageFragment newInstance(ArrayList<String> values) {
        Bundle args = new Bundle();
        args.putStringArrayList("images", values);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        GridView backdropGridView = (GridView) view.findViewById(R.id.backdrop_gridView);
        Bundle arguments = getArguments();
        values = arguments.getStringArrayList("images");
        backdropGridView.setAdapter(new ImageAdapter(getContext(), values));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}

