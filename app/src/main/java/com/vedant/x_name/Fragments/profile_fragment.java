package com.vedant.x_name.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.vedant.x_name.Activities.PaymentGateway;
import com.vedant.x_name.Model.Users;
import com.vedant.x_name.R;
import com.vedant.x_name.databinding.FragmentProfileFragmentBinding;

import java.util.HashMap;
import java.util.Objects;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class profile_fragment extends Fragment {

    FragmentProfileFragmentBinding binding;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;

    public profile_fragment() {
        // Required empty public constructor
    }


    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri uri = data.getData();
                            binding.profileImage.setImageURI(uri);
                           final StorageReference reference =
                                    FirebaseStorage.getInstance().getReference().child("profile pictures")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
                            Toast.makeText(getContext(),    FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profilePic") + "",
                                    Toast.LENGTH_SHORT).show();
                            reference.putFile(uri).addOnSuccessListener(taskSnapshot -> reference.getDownloadUrl().addOnSuccessListener(uri1 -> {
//                                FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
//                                        .child("profilePic").setValue(uri1.toString());


                                Toast.makeText(getContext(), "Profile Picture updated",
                                        Toast.LENGTH_SHORT).show();
                            }));

                        }
                    }
                }

            });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileFragmentBinding.inflate(inflater, container, false);

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database.getReference().child("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users users = snapshot.getValue(Users.class);
                        assert users != null;
                        Picasso.get()
                                .load(users.getProfilePic())
                                .placeholder(R.drawable.user)
                                .into(binding.profileImage);

                        binding.etUserName.setText(users.getUserName());
                        binding.etStatus.setText(users.getAbout());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        binding.coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),PaymentGateway.class));
            }
        });

        binding.plus.setOnClickListener(v -> openSomeActivityForResult());


        binding.save.setOnClickListener(v -> {
            String about = binding.etStatus.getText().toString();
            String userName = binding.etUserName.getText().toString();

            HashMap<String, Object> obj = new HashMap<>();
            obj.put("userName", userName);
            obj.put("about", about);

            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                    .updateChildren(obj);

            Toast.makeText(getContext(), "Details Updated", Toast.LENGTH_SHORT).show();
        });
        return binding.getRoot();
    }
    public void openSomeActivityForResult() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);
    }



}
