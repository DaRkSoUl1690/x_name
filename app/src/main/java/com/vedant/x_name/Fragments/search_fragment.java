package com.vedant.x_name.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vedant.x_name.Adapters.UserAdapter;
import com.vedant.x_name.Adapters.searchAdapter;
import com.vedant.x_name.Model.Users;
import com.vedant.x_name.R;
import com.vedant.x_name.databinding.FragmentMessageFragmentBinding;
import com.vedant.x_name.databinding.FragmentSearchFragmentBinding;

import java.util.ArrayList;

public class search_fragment extends Fragment {

    FragmentSearchFragmentBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    searchAdapter adapter;

    public search_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchFragmentBinding.inflate(inflater, container, false);

        binding.searchRecyclerView.setHasFixedSize(true);
        adapter =  new searchAdapter(list, getContext());
        binding.searchRecyclerView.setAdapter(adapter);
        binding.searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUsers(s.toString());
                list.clear();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return binding.getRoot();
    }
    private void searchUsers(@NonNull String s) {

        Query queryUsers = FirebaseDatabase.getInstance().getReference().child("Users")
                .orderByChild("userName").startAt(s).endAt(s + "\uf8ff");

        queryUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users users = dataSnapshot.getValue(Users.class);
                        list.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
