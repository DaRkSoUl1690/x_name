package com.vedant.x_name.MainRecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import com.bumptech.glide.RequestManager;
import com.vedant.x_name.Model.MediaObject;
import com.vedant.x_name.R;


public class VideoPlayerViewHolder extends RecyclerView.ViewHolder {

    FrameLayout media_container;
    TextView title;
//    name,no_likes,no_comments
    ImageView thumbnail, volumeControl;
    ProgressBar progressBar;
    View parent;
    RequestManager requestManager;
    ImageView shareBtn,likeBtn,commentBtn,likeAnimate,download;
    Button followBtn;
    CircleImageView profile_img;

    public VideoPlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
//        media_container = itemView.findViewById(R.id.media_container);
//        thumbnail = itemView.findViewById(R.id.thumbnail);
        title = itemView.findViewById(R.id.title);
//        name = itemView.findViewById(R.id.textView8);
//        progressBar = itemView.findViewById(R.id.progressBar);
//        volumeControl = itemView.findViewById(R.id.volumeControl);
//        shareBtn = itemView.findViewById(R.id.imageVie9);
        likeBtn = itemView.findViewById(R.id.imageView6);
        commentBtn = itemView.findViewById(R.id.imageView7);
//        followBtn = itemView.findViewById(R.id.btnEdit);
        profile_img = itemView.findViewById(R.id.circleImageView);
//        no_likes = itemView.findViewById(R.id.textView9);
//        no_comments = itemView.findViewById(R.id.textView10);
        likeAnimate = itemView.findViewById(R.id.imageView5);
//        download = itemView.findViewById(R.id.imageView23);
    }

    public void onBind(MediaObject mediaObject, RequestManager requestManager) {
        this.requestManager = requestManager;
        parent.setTag(this);
        title.setText(mediaObject.getDescription()+",\n"+mediaObject.getDate());

        this.requestManager.load(mediaObject.getThumbnail()).into(thumbnail);
    }

}
