package com.vedant.x_name.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.vedant.x_name.Model.Users;
import com.vedant.x_name.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class searchAdapter extends RecyclerView.Adapter<searchAdapter.ViewHolder> {

    private ArrayList<Users> list;

    public searchAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    private final Context context;
    private FirebaseUser mFirebaseUser;


    @NonNull
    @Override
    public searchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new searchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchAdapter.ViewHolder holder, int position) {
        Users users = list.get(position);
        // set image from the list we fetched from firebase in chat_fragment if found , otherwise
        // not
        Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.user).into(holder.img);
        // set username from the list we fetched from firebase in chat_fragment
        holder.userName.setText(users.getUserName());
        holder.status.setText(users.getAbout());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView userName, status;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.searchProfileImage);
            userName = itemView.findViewById(R.id.userName);
            status = itemView.findViewById(R.id.searchStatus);
        }
    }
}
