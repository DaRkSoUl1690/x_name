package com.vedant.x_name.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vedant.x_name.Model.MediaObject;
import com.vedant.x_name.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    public HomeAdapter(List<MediaObject> mediaObjectList, Context context) {
        this.mediaObjectList = mediaObjectList;
        this.context = context;
    }

    List<MediaObject> mediaObjectList;
    Context context;

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view  = LayoutInflater.from(context).inflate(R.layout.layout_home,
                       parent,false);

       return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
//           MediaObject mediaObject = mediaObjectList.get(position);
//           Glide.with(context).load(R.drawable.disk2).into(holder.sound_dis);
    }

    @Override
    public int getItemCount() {
        return mediaObjectList.size();
    }
    public static class HomeViewHolder extends RecyclerView.ViewHolder{
//        ImageView sound_dis;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
//             sound_dis = (ImageView) itemView.findViewById(R.id.sound_dis);


        }
    }
}
