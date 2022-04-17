package com.vedant.x_name.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vedant.x_name.Adapters.HomeAdapter;
import com.vedant.x_name.MainRecyclerView.VerticalSpacingItemDecorator;
import com.vedant.x_name.MainRecyclerView.VideoPlayerRecyclerView;
import com.vedant.x_name.Model.MediaObject;
import com.vedant.x_name.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class home_fragment extends Fragment {

    private List<MediaObject> mediaObjectList = new ArrayList<>();
    private HomeAdapter HomeAdapter;
    private RecyclerView recyclerView;
//    private VideoPlayerRecyclerView recyclerView;
//    ArrayList<MediaObject> mediaObjects = new ArrayList<>();
    public home_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home_fragment, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
//        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(0);
//        recyclerView.addItemDecoration(itemDecorator);

        SnapHelper mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(recyclerView);



        mediaObjectList.add(new MediaObject("", "", "", "", "", "", "", "", "", ""));
        mediaObjectList.add(new MediaObject("", "", "", "", "", "", "", "", "", ""));
        mediaObjectList.add(new MediaObject("", "", "", "", "", "", "", "", "", ""));
        mediaObjectList.add(new MediaObject("", "", "", "", "", "", "", "", "", ""));

        HomeAdapter = new HomeAdapter(mediaObjectList, getContext());
        recyclerView.setAdapter(HomeAdapter);
        HomeAdapter.notifyDataSetChanged();

        return root;
    }

}
