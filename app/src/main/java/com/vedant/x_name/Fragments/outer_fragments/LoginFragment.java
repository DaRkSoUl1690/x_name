package com.vedant.x_name.Fragments.outer_fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.vedant.x_name.Activities.MainActivity;
import com.vedant.x_name.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LoginFragment extends Fragment {

    Context mContext;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    EditText email, password;
    TextView textView;
    Button login;
    ProgressDialog proDialog;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            mContext = context;
        }
    }


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();



        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        login = root.findViewById(R.id.button2);
        textView = root.findViewById(R.id.textView3);


        textView.setOnClickListener(v -> {
            ForgettenPass forgotPass = new ForgettenPass();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_container, forgotPass).addToBackStack(null).commit();
        });

        login.setOnClickListener(v -> {
            try {
                if (email != null && password != null) {
                    proDialog = ProgressDialog.show(mContext, null, null, false, true);
                    proDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    proDialog.setContentView(R.layout.progress_bar);

                    mAuth.signInWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnCompleteListener(task -> {
                        proDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {

                            Toast.makeText(mContext, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            } catch (Exception e) {


                Toast.makeText(getActivity(), "please fill your Email and Password",
                        Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });


        return root;
    }
}
