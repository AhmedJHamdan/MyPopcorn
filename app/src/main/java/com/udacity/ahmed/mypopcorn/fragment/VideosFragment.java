package com.udacity.ahmed.mypopcorn.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.adapter.VideoAdapter;
import com.udacity.ahmed.mypopcorn.pojo.Video.VideoResult;

public class VideosFragment extends Fragment {

    ArrayList<VideoResult> values;

    private List<VideoResult> videos;

    public static VideosFragment newInstance(ArrayList<VideoResult> videos) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("videos", videos);
        VideosFragment fragment = new VideosFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        GridView backdropGridView = (GridView) view.findViewById(R.id.backdrop_gridView);
        Bundle arguments = getArguments();
        values = arguments.getParcelableArrayList("videos");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), values);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(videoAdapter);
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

