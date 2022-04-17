package com.vedant.x_name.Fragments;

import android.os.Bundle;
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
import com.vedant.x_name.Model.Users;
import com.vedant.x_name.databinding.FragmentMessageFragmentBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class message_fragment extends Fragment {

    FragmentMessageFragmentBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    UserAdapter adapter;

    public message_fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMessageFragmentBinding.inflate(inflater, container, false);
                retrieveAllUsers();

        binding.searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUsers(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return binding.getRoot();
    }

    public void retrieveAllUsers() {
        DatabaseReference refUsers = FirebaseDatabase.getInstance().getReference().child("Users");
        refUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users users = dataSnapshot.getValue(Users.class);
                    // changes made 25/10/21
                    assert users != null;
                    users.setUserId(dataSnapshot.getKey());
                    if (!users.getUserId().equals(FirebaseAuth.getInstance().getUid())) {
                        list.add(users);
                    }

                }
                adapter = new UserAdapter(list, getContext());
                binding.chatRecyclerView.setAdapter(adapter);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                binding.chatRecyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                    // changes made 25/10/21
                    assert users != null;
                    users.setUserId(dataSnapshot.getKey());
                    if (!users.getUserId().equals(FirebaseAuth.getInstance().getUid())) {
                        list.add(users);
                    }

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
