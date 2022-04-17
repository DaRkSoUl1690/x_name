package com.vedant.x_name.Fragments.outer_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.vedant.x_name.R;


public class ForgettenPass extends Fragment {

    FirebaseAuth mAuth;
    TextView email_send;
    Button reset_button;

    public ForgettenPass() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_forgetten_pass, container, false);

        mAuth = FirebaseAuth.getInstance();

        email_send = root.findViewById(R.id.email_send);
        reset_button = root.findViewById(R.id.reset_button);

        reset_button.setOnClickListener(v -> mAuth.sendPasswordResetEmail(email_send.getText().toString())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "email send", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), "email failed",
                                Toast.LENGTH_SHORT).show();

                    }
                }));

        return root;
    }
}
